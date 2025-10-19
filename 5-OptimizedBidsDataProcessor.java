package org.example.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.builder.MetricMeasureBuilder;
import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.dto.MetricMeasure;
import org.example.strategy.StrategyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 优化的数据处理器 - 整合流式处理、策略模式、建造者模式
 */
@Slf4j
@Service
public class OptimizedBidsDataProcessor {

    // 线程池配置
    private final ExecutorService executorService;

    @Autowired
    private StrategyManager strategyManager;

    @Autowired
    private MetadataService metadataService;

    @Autowired
    private DataQueryService dataQueryService;

    public OptimizedBidsDataProcessor() {
        int processors = Runtime.getRuntime().availableProcessors();
        this.executorService = new ThreadPoolExecutor(
                processors,
                processors * 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000),
                new ThreadFactory() {
                    private int count = 0;
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r, "bids-processor-" + count++);
                        thread.setDaemon(false);
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        log.info("初始化数据处理器，线程池大小: 核心={}, 最大={}", processors, processors * 2);
    }

    /**
     * 主处理方法 - 完整版
     */
    public ProcessResult processData(ProcessContext context) {
        long startTime = System.currentTimeMillis();
        ProcessResult processResult = new ProcessResult();

        try {
            log.info("开始处理数据，参数: {}", context);

            // 1. 查询第一页，获取总数
            PageResult<BidsResp> firstPage = dataQueryService.queryFirstPage(context.getPageSize());
            long total = firstPage.getTotal();
            processResult.setTotalRecords(total);

            if (total == 0) {
                log.info("没有数据需要处理");
                processResult.setData(Collections.emptyList());
                return processResult;
            }

            log.info("查询到总记录数: {}, 每页: {}", total, context.getPageSize());

            // 2. 计算总页数
            int totalPages = (int) Math.ceil((double) total / context.getPageSize());
            processResult.setTotalPages(totalPages);

            // 3. 加载元数据（一次性加载，所有线程共享）
            FilterMetadata filterMetadata = metadataService.loadFilterMetadata(context);
            
            // 记录激活的策略
            List<String> activeStrategies = strategyManager.getActiveFilterStrategyNames(context);
            log.info("激活的过滤策略: {}", activeStrategies);
            processResult.setActiveStrategies(activeStrategies);

            // 4. 使用ConcurrentHashMap管理所有Builder
            ConcurrentHashMap<String, MetricMeasureBuilder> builderMap = new ConcurrentHashMap<>();

            // 5. 处理第一页（同步）
            int firstPageProcessed = processPageData(
                    firstPage.getData(),
                    filterMetadata,
                    context,
                    builderMap
            );
            processResult.incrementProcessedRecords(firstPageProcessed);

            log.info("第1页处理完成，处理 {}/{} 条记录", 
                    firstPageProcessed, firstPage.getData().size());

            // 6. 并行处理剩余页
            if (totalPages > 1) {
                List<CompletableFuture<Integer>> futures = new ArrayList<>();

                for (int page = 2; page <= totalPages; page++) {
                    final int currentPage = page;

                    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            List<BidsResp> pageData = dataQueryService.queryPage(
                                    currentPage, 
                                    context.getPageSize()
                            );

                            int processed = processPageData(
                                    pageData,
                                    filterMetadata,
                                    context,
                                    builderMap
                            );

                            log.info("第{}页处理完成，处理 {}/{} 条记录",
                                    currentPage, processed, pageData.size());

                            return processed;

                        } catch (Exception e) {
                            log.error("处理第{}页失败", currentPage, e);
                            processResult.addError("第" + currentPage + "页处理失败: " + e.getMessage());
                            return 0;
                        }
                    }, executorService);

                    futures.add(future);
                }

                // 等待所有任务完成并统计结果
                CompletableFuture<Void> allOf = CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[0])
                );

                allOf.join();

                // 收集所有页的处理结果
                for (CompletableFuture<Integer> future : futures) {
                    try {
                        Integer processed = future.get();
                        processResult.incrementProcessedRecords(processed);
                    } catch (Exception e) {
                        log.error("获取处理结果失败", e);
                    }
                }
            }

            // 7. 构建最终结果
            List<MetricMeasure> finalResult = builderMap.values().stream()
                    .map(MetricMeasureBuilder::build)
                    .sorted(Comparator.comparing(MetricMeasure::getPeriodId))
                    .collect(Collectors.toList());

            processResult.setData(finalResult);
            processResult.setSuccess(true);

            long duration = System.currentTimeMillis() - startTime;
            processResult.setProcessDuration(duration);

            log.info("数据处理完成！总记录: {}, 处理: {}, 过滤: {}, 结果: {}, 耗时: {}ms",
                    total,
                    processResult.getProcessedRecords(),
                    total - processResult.getProcessedRecords(),
                    finalResult.size(),
                    duration);

            return processResult;

        } catch (Exception e) {
            log.error("处理数据失败", e);
            processResult.setSuccess(false);
            processResult.addError("处理失败: " + e.getMessage());
            throw new RuntimeException("处理数据失败", e);
        }
    }

    /**
     * 处理单页数据 - 核心流式处理逻辑
     * 
     * @return 实际处理的记录数
     */
    private int processPageData(
            List<BidsResp> pageData,
            FilterMetadata filterMetadata,
            ProcessContext context,
            ConcurrentHashMap<String, MetricMeasureBuilder> builderMap) {

        if (pageData == null || pageData.isEmpty()) {
            return 0;
        }

        // 流式处理：过滤 -> 转换 -> 聚合
        long count = pageData.stream()
                // 并行处理（数据量大时）
                .parallel()
                // 应用所有过滤策略
                .filter(resp -> strategyManager.executeFilterStrategies(resp, filterMetadata, context))
                // 转换并聚合
                .peek(resp -> {
                    String periodId = resp.getPeriod();

                    // 获取或创建Builder（线程安全）
                    MetricMeasureBuilder builder = builderMap.computeIfAbsent(
                            periodId,
                            MetricMeasureBuilder::create
                    );

                    // 构建metricKey
                    String metricKey = buildMetricKey(resp);

                    // 应用转换策略
                    Measure measure = strategyManager.executeTransformStrategy(
                            resp,
                            filterMetadata,
                            context
                    );

                    // 添加到Builder（线程安全）
                    builder.addMeasure(metricKey, measure);
                })
                .count();

        return (int) count;
    }

    /**
     * 构建Metric的Key
     */
    private String buildMetricKey(BidsResp resp) {
        return resp.getMetricCode() + ":::" +
               resp.getOrgCode() + ":::" +
               resp.getDomainCode();
    }

    /**
     * 销毁线程池
     */
    @PreDestroy
    public void destroy() {
        log.info("开始关闭线程池...");
        if (executorService != null && !executorService.isShutdown()) {
executorService.shutdown();
try {
if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
log.warn("60");
executorService.shutdownNow();
if (!executorService.awaitTermination(30, TimeUnit.SECONDS)) {
log.error("");
}
}
log.info("");
} catch (InterruptedException e) {
log.error("", e);
executorService.shutdownNow();
Thread.currentThread().interrupt();
}
}
}