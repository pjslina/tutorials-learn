package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.dto.MetricMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class OptimizedBidsDataProcessor {

    // 线程池配置
    private final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 2,
            60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            new ThreadFactory() {
                private int count = 0;
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "bids-processor-" + count++);
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    @Autowired
    private BidsFilterService filterService; // 过滤服务

    @Autowired
    private MetadataService metadataService; // 元数据服务

    /**
     * 主处理方法 - 优化版
     */
    public List<MetricMeasure> processDataOptimized(ProcessContext context) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 1. 查询第一页，获取总数
            PageResult<BidsResp> firstPage = queryFirstPage(context.getPageSize());
            long total = firstPage.getTotal();
            
            log.info("开始处理数据，总记录数: {}, 每页: {}", total, context.getPageSize());

            // 2. 计算总页数
            int totalPages = (int) Math.ceil((double) total / context.getPageSize());
            
            // 3. 准备元数据（一次性加载，所有线程共享）
            FilterMetadata filterMetadata = metadataService.loadFilterMetadata(context);
            
            // 4. 使用ConcurrentHashMap直接构建最终结果，避免中间对象
            ConcurrentHashMap<String, MetricMeasureBuilder> resultBuilders = new ConcurrentHashMap<>();

            // 5. 处理第一页（同步）
            processPageData(firstPage.getData(), filterMetadata, context, resultBuilders);

            // 6. 并行处理剩余页
            if (totalPages > 1) {
                List<CompletableFuture<Void>> futures = new ArrayList<>();

                for (int page = 2; page <= totalPages; page++) {
                    final int currentPage = page;
                    
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        try {
                            List<BidsResp> pageData = queryPage(currentPage, context.getPageSize());
                            processPageData(pageData, filterMetadata, context, resultBuilders);
                        } catch (Exception e) {
                            log.error("处理第{}页失败", currentPage, e);
                        }
                    }, executorService);

                    futures.add(future);
                }

                // 等待所有任务完成
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            }

            // 7. 构建最终结果
            List<MetricMeasure> result = resultBuilders.values().stream()
                    .map(MetricMeasureBuilder::build)
                    .collect(Collectors.toList());

            log.info("处理完成，耗时: {}ms, 结果数: {}", 
                    System.currentTimeMillis() - startTime, result.size());

            return result;

        } catch (Exception e) {
            log.error("处理数据失败", e);
            throw new RuntimeException("处理数据失败", e);
        }
    }

    /**
     * 处理单页数据 - 核心处理逻辑
     * 直接从BidsResp转换到最终结果，无需中间对象
     */
    private void processPageData(
            List<BidsResp> pageData,
            FilterMetadata filterMetadata,
            ProcessContext context,
            ConcurrentHashMap<String, MetricMeasureBuilder> resultBuilders) {

        if (pageData == null || pageData.isEmpty()) {
            return;
        }

        // 流式处理：过滤 -> 转换 -> 分组 -> 聚合
        pageData.stream()
                // 1. 应用复杂的过滤规则
                .filter(resp -> filterService.shouldInclude(resp, filterMetadata, context))
                // 2. 并行处理（如果数据量大）
                .parallel()
                // 3. 按period分组处理
                .forEach(resp -> {
                    String periodId = resp.getPeriod();
                    
                    // 获取或创建Builder（线程安全）
                    MetricMeasureBuilder builder = resultBuilders.computeIfAbsent(
                            periodId, 
                            k -> new MetricMeasureBuilder(periodId)
                    );

                    // 构建key
                    String metricKey = buildMetricKey(resp);
                    
                    // 创建Measure
                    Measure measure = convertToMeasure(resp);
                    
                    // 添加到Builder（线程安全）
                    builder.addMeasure(metricKey, measure);
                });
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
     * BidsResp转Measure（轻量级转换）
     */
    private Measure convertToMeasure(BidsResp resp) {
        Measure measure = new Measure();
        measure.setMeasureCode(resp.getMeasureCode());
        measure.setMeasureUnit(resp.getMeasureUnit());
        measure.setMeasureValue(resp.getMeasureValue());
        measure.setCurrency(resp.getCurrency());
        return measure;
    }

    /**
     * 查询第一页
     */
    private PageResult<BidsResp> queryFirstPage(int pageSize) {
        // TODO: 实现查询逻辑
        return new PageResult<>();
    }

    /**
     * 查询指定页
     */
    private List<BidsResp> queryPage(int page, int pageSize) {
        // TODO: 实现查询逻辑
        return new ArrayList<>();
    }

    /**
     * MetricMeasure构建器 - 线程安全
     */
    private static class MetricMeasureBuilder {
        private final String periodId;
        private final ConcurrentHashMap<String, List<Measure>> metricMap;

        public MetricMeasureBuilder(String periodId) {
            this.periodId = periodId;
            this.metricMap = new ConcurrentHashMap<>();
        }

        public void addMeasure(String key, Measure measure) {
            metricMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>())
                     .add(measure);
        }

        public MetricMeasure build() {
            MetricMeasure result = new MetricMeasure();
            result.setPeriodId(periodId);
            
            // 转换为普通HashMap（不再需要并发访问）
            Map<String, List<Measure>> finalMap = new HashMap<>(metricMap.size());
            metricMap.forEach((key, measures) -> {
                // 可以在这里做额外的聚合或排序
                finalMap.put(key, new ArrayList<>(measures));
            });
            
            result.setMetricMap(finalMap);
            return result;
        }
    }

    /**
     * 处理上下文 - 封装所有输入参数
     */
    @lombok.Data
    public static class ProcessContext {
        private int pageSize;
        private Map<String, Object> inputParams; // 入参
        private Set<String> requiredMetricCodes; // 需要的指标代码
        private Set<String> excludeOrgCodes; // 排除的组织代码
        // ... 其他过滤条件
    }

    /**
     * 过滤元数据 - 封装所有过滤需要的元数据
     */
    @lombok.Data
    public static class FilterMetadata {
        private Map<String, String> metricMapping; // 指标映射
        private Set<String> validDomainCodes; // 有效的域代码
        private Map<String, List<String>> orgHierarchy; // 组织层级
        // ... 其他元数据
    }

    /**
     * 分页结果
     */
    @lombok.Data
    public static class PageResult<T> {
        private long total;
        private List<T> data;
    }

    @javax.annotation.PreDestroy
    public void destroy() {
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}