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

    // �̳߳�����
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
    private BidsFilterService filterService; // ���˷���

    @Autowired
    private MetadataService metadataService; // Ԫ���ݷ���

    /**
     * �������� - �Ż���
     */
    public List<MetricMeasure> processDataOptimized(ProcessContext context) {
        long startTime = System.currentTimeMillis();
        
        try {
            // 1. ��ѯ��һҳ����ȡ����
            PageResult<BidsResp> firstPage = queryFirstPage(context.getPageSize());
            long total = firstPage.getTotal();
            
            log.info("��ʼ�������ݣ��ܼ�¼��: {}, ÿҳ: {}", total, context.getPageSize());

            // 2. ������ҳ��
            int totalPages = (int) Math.ceil((double) total / context.getPageSize());
            
            // 3. ׼��Ԫ���ݣ�һ���Լ��أ������̹߳���
            FilterMetadata filterMetadata = metadataService.loadFilterMetadata(context);
            
            // 4. ʹ��ConcurrentHashMapֱ�ӹ������ս���������м����
            ConcurrentHashMap<String, MetricMeasureBuilder> resultBuilders = new ConcurrentHashMap<>();

            // 5. �����һҳ��ͬ����
            processPageData(firstPage.getData(), filterMetadata, context, resultBuilders);

            // 6. ���д���ʣ��ҳ
            if (totalPages > 1) {
                List<CompletableFuture<Void>> futures = new ArrayList<>();

                for (int page = 2; page <= totalPages; page++) {
                    final int currentPage = page;
                    
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        try {
                            List<BidsResp> pageData = queryPage(currentPage, context.getPageSize());
                            processPageData(pageData, filterMetadata, context, resultBuilders);
                        } catch (Exception e) {
                            log.error("�����{}ҳʧ��", currentPage, e);
                        }
                    }, executorService);

                    futures.add(future);
                }

                // �ȴ������������
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            }

            // 7. �������ս��
            List<MetricMeasure> result = resultBuilders.values().stream()
                    .map(MetricMeasureBuilder::build)
                    .collect(Collectors.toList());

            log.info("������ɣ���ʱ: {}ms, �����: {}", 
                    System.currentTimeMillis() - startTime, result.size());

            return result;

        } catch (Exception e) {
            log.error("��������ʧ��", e);
            throw new RuntimeException("��������ʧ��", e);
        }
    }

    /**
     * ����ҳ���� - ���Ĵ����߼�
     * ֱ�Ӵ�BidsRespת�������ս���������м����
     */
    private void processPageData(
            List<BidsResp> pageData,
            FilterMetadata filterMetadata,
            ProcessContext context,
            ConcurrentHashMap<String, MetricMeasureBuilder> resultBuilders) {

        if (pageData == null || pageData.isEmpty()) {
            return;
        }

        // ��ʽ�������� -> ת�� -> ���� -> �ۺ�
        pageData.stream()
                // 1. Ӧ�ø��ӵĹ��˹���
                .filter(resp -> filterService.shouldInclude(resp, filterMetadata, context))
                // 2. ���д��������������
                .parallel()
                // 3. ��period���鴦��
                .forEach(resp -> {
                    String periodId = resp.getPeriod();
                    
                    // ��ȡ�򴴽�Builder���̰߳�ȫ��
                    MetricMeasureBuilder builder = resultBuilders.computeIfAbsent(
                            periodId, 
                            k -> new MetricMeasureBuilder(periodId)
                    );

                    // ����key
                    String metricKey = buildMetricKey(resp);
                    
                    // ����Measure
                    Measure measure = convertToMeasure(resp);
                    
                    // ��ӵ�Builder���̰߳�ȫ��
                    builder.addMeasure(metricKey, measure);
                });
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
     * BidsRespתMeasure��������ת����
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
     * ��ѯ��һҳ
     */
    private PageResult<BidsResp> queryFirstPage(int pageSize) {
        // TODO: ʵ�ֲ�ѯ�߼�
        return new PageResult<>();
    }

    /**
     * ��ѯָ��ҳ
     */
    private List<BidsResp> queryPage(int page, int pageSize) {
        // TODO: ʵ�ֲ�ѯ�߼�
        return new ArrayList<>();
    }

    /**
     * MetricMeasure������ - �̰߳�ȫ
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
            
            // ת��Ϊ��ͨHashMap��������Ҫ�������ʣ�
            Map<String, List<Measure>> finalMap = new HashMap<>(metricMap.size());
            metricMap.forEach((key, measures) -> {
                // ����������������ľۺϻ�����
                finalMap.put(key, new ArrayList<>(measures));
            });
            
            result.setMetricMap(finalMap);
            return result;
        }
    }

    /**
     * ���������� - ��װ�����������
     */
    @lombok.Data
    public static class ProcessContext {
        private int pageSize;
        private Map<String, Object> inputParams; // ���
        private Set<String> requiredMetricCodes; // ��Ҫ��ָ�����
        private Set<String> excludeOrgCodes; // �ų�����֯����
        // ... ������������
    }

    /**
     * ����Ԫ���� - ��װ���й�����Ҫ��Ԫ����
     */
    @lombok.Data
    public static class FilterMetadata {
        private Map<String, String> metricMapping; // ָ��ӳ��
        private Set<String> validDomainCodes; // ��Ч�������
        private Map<String, List<String>> orgHierarchy; // ��֯�㼶
        // ... ����Ԫ����
    }

    /**
     * ��ҳ���
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