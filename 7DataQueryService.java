### 6. ���ݲ�ѯ����
```java
package org.example.service;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.PageResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ݲ�ѯ����
 */
@Service
public class DataQueryService {

    /**
     * ��ѯ��һҳ
     */
    public PageResult<BidsResp> queryFirstPage(int pageSize) {
        // TODO: ʵ�־�������ݿ��ѯ�߼�
        // ʾ����
        // return yourRepository.findAll(PageRequest.of(0, pageSize));
        
        PageResult<BidsResp> result = new PageResult<>();
        result.setTotal(1000); // ��������
        result.setPageNum(1);
        result.setPageSize(pageSize);
        result.setData(mockQueryData(1, pageSize));
        
        return result;
    }

    /**
     * ��ѯָ��ҳ
     */
    public List<BidsResp> queryPage(int pageNum, int pageSize) {
        // TODO: ʵ�־�������ݿ��ѯ�߼�
        // ʾ����
        // return yourRepository.findAll(PageRequest.of(pageNum - 1, pageSize)).getContent();
        
        return mockQueryData(pageNum, pageSize);
    }

    /**
     * ģ���ѯ����
     */
    private List<BidsResp> mockQueryData(int pageNum, int pageSize) {
        List<BidsResp> list = new ArrayList<>();
        // TODO: ʵ�ʲ�ѯ�߼�
        return list;
    }
}
```

### 7. Ԫ���ݷ���
```java
package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Ԫ���ݷ��� - ������غͻ���Ԫ����
 */
@Slf4j
@Service
public class MetadataService {

    /**
     * ���ع��������Ԫ���ݣ������棩
     */
    @Cacheable(value = "filterMetadata", key = "#context.hashCode()")
    public FilterMetadata loadFilterMetadata(ProcessContext context) {
        log.info("���ع���Ԫ����...");
        
        FilterMetadata metadata = new FilterMetadata();

        // 1. ����ָ��ӳ���ϵ
        metadata.setMetricMapping(loadMetricMapping());

        // 2. ������Ч�������
        metadata.setValidDomainCodes(loadValidDomainCodes(context));

        // 3. ������֯�㼶��ϵ
        metadata.setOrgHierarchy(loadOrgHierarchy(context));

        // 4. ���ػ�����Ϣ�������Ҫ��
        if (Boolean.TRUE.equals(context.getInputParams().get("isFinancial"))) {
            metadata.setExchangeRates(loadExchangeRates());
        }

        log.info("Ԫ���ݼ������");
        return metadata;
    }

    /**
     * ����ָ��ӳ���ϵ
     */
    private Map<String, String> loadMetricMapping() {
        // TODO: �����ݿ�������ļ�����
        Map<String, String> mapping = new HashMap<>();
        mapping.put("REVENUE", "Ӫҵ����");
        mapping.put("PROFIT", "����");
        mapping.put("COST", "�ɱ�");
        return mapping;
    }

    /**
     * ������Ч�������
     */
    private Set<String> loadValidDomainCodes(ProcessContext context) {
        // TODO: �����ݿ����
        Set<String> codes = new HashSet<>();
        codes.add("FINANCE");
        codes.add("OPERATIONS");
        codes.add("HR");
        return codes;
    }

    /**
     * ������֯�㼶��ϵ
     */
    private Map<String, List<String>> loadOrgHierarchy(ProcessContext context) {
        // TODO: �����ݿ������֯��
        Map<String, List<String>> hierarchy = new HashMap<>();
        hierarchy.put("HQ", Arrays.asList("DEPT01", "DEPT02", "DEPT03"));
        hierarchy.put("DEPT01", Arrays.asList("TEAM01", "TEAM02"));
        return hierarchy;
    }

    /**
     * ���ػ�����Ϣ
     */
    private Map<String, Double> loadExchangeRates() {
        // TODO: ���ⲿAPI�����ݿ����ʵʱ����
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD_CNY", 7.2);
        rates.put("EUR_CNY", 7.8);
        rates.put("JPY_CNY", 0.05);
        return rates;
    }
}
```

### 8. Controller��ʹ��ʾ��
```java
package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.MetricMeasure;
import org.example.service.OptimizedBidsDataProcessor;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.service.OptimizedBidsDataProcessor.ProcessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * ���ݴ��������
 */
@Slf4j
@RestController
@RequestMapping("/api/bids")
public class BidsController {

    @Autowired
    private OptimizedBidsDataProcessor processor;

    /**
     * �������� - �����汾
     */
    @GetMapping("/metrics")
    public ResponseEntity<List<MetricMeasure>> getMetrics(
            @RequestParam(defaultValue = "100") int pageSize) {
        
        ProcessContext context = new ProcessContext(pageSize);
        ProcessResult result = processor.processData(context);
        
        return ResponseEntity.ok(result.getData());
    }

    /**
     * �������� - �����汾�������в�����
     */
    @PostMapping("/metrics/advanced")
    public ResponseEntity<ProcessResult> getMetricsAdvanced(
            @RequestBody MetricsRequest request) {
        
        // ��������������
        ProcessContext context = new ProcessContext()
                .withPageSize(request.getPageSize())
                .withRequiredMetricCodes(request.getMetricCodes())
                .withExcludeOrgCodes(request.getExcludeOrgCodes())
                .withPeriodRange(request.getStartPeriod(), request.getEndPeriod());

        // ��Ӷ������
        if (request.isFinancial()) {
            context.withInputParam("isFinancial", true);
            context.withInputParam("targetCurrency", request.getTargetCurrency());
        }

        if (request.isStrictMode()) {
            context.withInputParam("strictMode", true);
        }

        if (request.getParentOrgCode() != null) {
            context.withInputParam("parentOrgCode", request.getParentOrgCode());
        }

        // ִ�д���
        ProcessResult result = processor.processData(context);
        
        return ResponseEntity.ok(result);
    }

    /**
     * ���������װ
     */
    @lombok.Data
    public static class MetricsRequest {
        private int pageSize = 100;
        private Set<String> metricCodes;
        private Set<String> excludeOrgCodes;
        private String startPeriod;
        private String endPeriod;
        private boolean financial = false;
        private String targetCurrency;
        private boolean strictMode = false;
        private String parentOrgCode;
    }
}
```

### 9. ������
```java
package org.example.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Ӧ������
 */
@Configuration
@EnableCaching
@EnableAsync
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    // ������������������Bean
}
```

### 10. ��Ԫ����ʾ��
```java
package org.example.service;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.impl.MetricCodeFilterStrategy;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ���Բ���
 */
class FilterStrategyTest {

    @Test
    void testMetricCodeFilterStrategy() {
        // ׼������
        MetricCodeFilterStrategy strategy = new MetricCodeFilterStrategy();
        
        BidsResp resp = new BidsResp();
        resp.setMetricCode("REVENUE");
        
        ProcessContext context = new ProcessContext();
        Set<String> requiredCodes = new HashSet<>();
        requiredCodes.add("REVENUE");
        requiredCodes.add("PROFIT");
        context.setRequiredMetricCodes(requiredCodes);
        
        FilterMetadata metadata = new FilterMetadata();
        
        // ����
        assertTrue(strategy.isApplicable(context));
        assertTrue(strategy.filter(resp, metadata, context));
        
        // ���Բ����б��е�ָ��
        resp.setMetricCode("COST");
        assertFalse(strategy.filter(resp, metadata, context));
    }
}
```

## �����ܽ�

### ��������

1. **����ģʽ������ʵ��**
   - �����������Ĳ��Խӿڣ�FilterStrategy��TransformStrategy��
   - ÿ������ְ��һ��������չ
   - ͨ��StrategyManagerͳһ����͵���
   - ֧�ֲ������ȼ��Ͷ�̬����

2. **������ģʽ������Ӧ��**
   - MetricMeasureBuilderʵ���̰߳�ȫ�Ĺ���
   - ֧����ʽ���ú���������
   - �ṩ�ϲ�����յȸ߼�����
   - ��ֹ�ظ�����

3. **��ʽ����ĸ�Чʵ��**
   - ʹ��Stream API�������ݴ���
   - ���������������ٶ�
   - �����м���󴴽�

4. **�ܹ�����**