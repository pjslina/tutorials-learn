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
 * �Ż������ݴ����� - ������ʽ��������ģʽ��������ģʽ
 */
@Slf4j
@Service
public class OptimizedBidsDataProcessor {

    // �̳߳�����
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

        log.info("��ʼ�����ݴ��������̳߳ش�С: ����={}, ���={}", processors, processors * 2);
    }

    /**
     * �������� - ������
     */
    public ProcessResult processData(ProcessContext context) {
        long startTime = System.currentTimeMillis();
        ProcessResult processResult = new ProcessResult();

        try {
            log.info("��ʼ�������ݣ�����: {}", context);

            // 1. ��ѯ��һҳ����ȡ����
            PageResult<BidsResp> firstPage = dataQueryService.queryFirstPage(context.getPageSize());
            long total = firstPage.getTotal();
            processResult.setTotalRecords(total);

            if (total == 0) {
                log.info("û��������Ҫ����");
                processResult.setData(Collections.emptyList());
                return processResult;
            }

            log.info("��ѯ���ܼ�¼��: {}, ÿҳ: {}", total, context.getPageSize());

            // 2. ������ҳ��
            int totalPages = (int) Math.ceil((double) total / context.getPageSize());
            processResult.setTotalPages(totalPages);

            // 3. ����Ԫ���ݣ�һ���Լ��أ������̹߳���
            FilterMetadata filterMetadata = metadataService.loadFilterMetadata(context);
            
            // ��¼����Ĳ���
            List<String> activeStrategies = strategyManager.getActiveFilterStrategyNames(context);
            log.info("����Ĺ��˲���: {}", activeStrategies);
            processResult.setActiveStrategies(activeStrategies);

            // 4. ʹ��ConcurrentHashMap��������Builder
            ConcurrentHashMap<String, MetricMeasureBuilder> builderMap = new ConcurrentHashMap<>();

            // 5. �����һҳ��ͬ����
            int firstPageProcessed = processPageData(
                    firstPage.getData(),
                    filterMetadata,
                    context,
                    builderMap
            );
            processResult.incrementProcessedRecords(firstPageProcessed);

            log.info("��1ҳ������ɣ����� {}/{} ����¼", 
                    firstPageProcessed, firstPage.getData().size());

            // 6. ���д���ʣ��ҳ
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

                            log.info("��{}ҳ������ɣ����� {}/{} ����¼",
                                    currentPage, processed, pageData.size());

                            return processed;

                        } catch (Exception e) {
                            log.error("�����{}ҳʧ��", currentPage, e);
                            processResult.addError("��" + currentPage + "ҳ����ʧ��: " + e.getMessage());
                            return 0;
                        }
                    }, executorService);

                    futures.add(future);
                }

                // �ȴ�����������ɲ�ͳ�ƽ��
                CompletableFuture<Void> allOf = CompletableFuture.allOf(
                        futures.toArray(new CompletableFuture[0])
                );

                allOf.join();

                // �ռ�����ҳ�Ĵ�����
                for (CompletableFuture<Integer> future : futures) {
                    try {
                        Integer processed = future.get();
                        processResult.incrementProcessedRecords(processed);
                    } catch (Exception e) {
                        log.error("��ȡ������ʧ��", e);
                    }
                }
            }

            // 7. �������ս��
            List<MetricMeasure> finalResult = builderMap.values().stream()
                    .map(MetricMeasureBuilder::build)
                    .sorted(Comparator.comparing(MetricMeasure::getPeriodId))
                    .collect(Collectors.toList());

            processResult.setData(finalResult);
            processResult.setSuccess(true);

            long duration = System.currentTimeMillis() - startTime;
            processResult.setProcessDuration(duration);

            log.info("���ݴ�����ɣ��ܼ�¼: {}, ����: {}, ����: {}, ���: {}, ��ʱ: {}ms",
                    total,
                    processResult.getProcessedRecords(),
                    total - processResult.getProcessedRecords(),
                    finalResult.size(),
                    duration);

            return processResult;

        } catch (Exception e) {
            log.error("��������ʧ��", e);
            processResult.setSuccess(false);
            processResult.addError("����ʧ��: " + e.getMessage());
            throw new RuntimeException("��������ʧ��", e);
        }
    }

    /**
     * ����ҳ���� - ������ʽ�����߼�
     * 
     * @return ʵ�ʴ���ļ�¼��
     */
    private int processPageData(
            List<BidsResp> pageData,
            FilterMetadata filterMetadata,
            ProcessContext context,
            ConcurrentHashMap<String, MetricMeasureBuilder> builderMap) {

        if (pageData == null || pageData.isEmpty()) {
            return 0;
        }

        // ��ʽ�������� -> ת�� -> �ۺ�
        long count = pageData.stream()
                // ���д�����������ʱ��
                .parallel()
                // Ӧ�����й��˲���
                .filter(resp -> strategyManager.executeFilterStrategies(resp, filterMetadata, context))
                // ת�����ۺ�
                .peek(resp -> {
                    String periodId = resp.getPeriod();

                    // ��ȡ�򴴽�Builder���̰߳�ȫ��
                    MetricMeasureBuilder builder = builderMap.computeIfAbsent(
                            periodId,
                            MetricMeasureBuilder::create
                    );

                    // ����metricKey
                    String metricKey = buildMetricKey(resp);

                    // Ӧ��ת������
                    Measure measure = strategyManager.executeTransformStrategy(
                            resp,
                            filterMetadata,
                            context
                    );

                    // ��ӵ�Builder���̰߳�ȫ��
                    builder.addMeasure(metricKey, measure);
                })
                .count();

        return (int) count;
    }

    /**
     * ����Metric��Key
     */
    private String buildMetricKey(BidsResp resp) {
        return resp.getMetricCode() + ":::" +
               resp.getOrgCode() + ":::" +
               resp.getDomainCode();
    }

    /**
     * �����̳߳�
     */
    @PreDestroy
    public void destroy() {
        log.info("��ʼ�ر��̳߳�...");
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