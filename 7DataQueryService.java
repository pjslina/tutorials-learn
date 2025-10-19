### 6. 数据查询服务
```java
package org.example.service;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.PageResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据查询服务
 */
@Service
public class DataQueryService {

    /**
     * 查询第一页
     */
    public PageResult<BidsResp> queryFirstPage(int pageSize) {
        // TODO: 实现具体的数据库查询逻辑
        // 示例：
        // return yourRepository.findAll(PageRequest.of(0, pageSize));
        
        PageResult<BidsResp> result = new PageResult<>();
        result.setTotal(1000); // 假设总数
        result.setPageNum(1);
        result.setPageSize(pageSize);
        result.setData(mockQueryData(1, pageSize));
        
        return result;
    }

    /**
     * 查询指定页
     */
    public List<BidsResp> queryPage(int pageNum, int pageSize) {
        // TODO: 实现具体的数据库查询逻辑
        // 示例：
        // return yourRepository.findAll(PageRequest.of(pageNum - 1, pageSize)).getContent();
        
        return mockQueryData(pageNum, pageSize);
    }

    /**
     * 模拟查询数据
     */
    private List<BidsResp> mockQueryData(int pageNum, int pageSize) {
        List<BidsResp> list = new ArrayList<>();
        // TODO: 实际查询逻辑
        return list;
    }
}
```

### 7. 元数据服务
```java
package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 元数据服务 - 负责加载和缓存元数据
 */
@Slf4j
@Service
public class MetadataService {

    /**
     * 加载过滤所需的元数据（带缓存）
     */
    @Cacheable(value = "filterMetadata", key = "#context.hashCode()")
    public FilterMetadata loadFilterMetadata(ProcessContext context) {
        log.info("加载过滤元数据...");
        
        FilterMetadata metadata = new FilterMetadata();

        // 1. 加载指标映射关系
        metadata.setMetricMapping(loadMetricMapping());

        // 2. 加载有效的域代码
        metadata.setValidDomainCodes(loadValidDomainCodes(context));

        // 3. 加载组织层级关系
        metadata.setOrgHierarchy(loadOrgHierarchy(context));

        // 4. 加载汇率信息（如果需要）
        if (Boolean.TRUE.equals(context.getInputParams().get("isFinancial"))) {
            metadata.setExchangeRates(loadExchangeRates());
        }

        log.info("元数据加载完成");
        return metadata;
    }

    /**
     * 加载指标映射关系
     */
    private Map<String, String> loadMetricMapping() {
        // TODO: 从数据库或配置文件加载
        Map<String, String> mapping = new HashMap<>();
        mapping.put("REVENUE", "营业收入");
        mapping.put("PROFIT", "利润");
        mapping.put("COST", "成本");
        return mapping;
    }

    /**
     * 加载有效的域代码
     */
    private Set<String> loadValidDomainCodes(ProcessContext context) {
        // TODO: 从数据库加载
        Set<String> codes = new HashSet<>();
        codes.add("FINANCE");
        codes.add("OPERATIONS");
        codes.add("HR");
        return codes;
    }

    /**
     * 加载组织层级关系
     */
    private Map<String, List<String>> loadOrgHierarchy(ProcessContext context) {
        // TODO: 从数据库加载组织树
        Map<String, List<String>> hierarchy = new HashMap<>();
        hierarchy.put("HQ", Arrays.asList("DEPT01", "DEPT02", "DEPT03"));
        hierarchy.put("DEPT01", Arrays.asList("TEAM01", "TEAM02"));
        return hierarchy;
    }

    /**
     * 加载汇率信息
     */
    private Map<String, Double> loadExchangeRates() {
        // TODO: 从外部API或数据库加载实时汇率
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD_CNY", 7.2);
        rates.put("EUR_CNY", 7.8);
        rates.put("JPY_CNY", 0.05);
        return rates;
    }
}
```

### 8. Controller层使用示例
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
 * 数据处理控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/bids")
public class BidsController {

    @Autowired
    private OptimizedBidsDataProcessor processor;

    /**
     * 处理数据 - 基础版本
     */
    @GetMapping("/metrics")
    public ResponseEntity<List<MetricMeasure>> getMetrics(
            @RequestParam(defaultValue = "100") int pageSize) {
        
        ProcessContext context = new ProcessContext(pageSize);
        ProcessResult result = processor.processData(context);
        
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 处理数据 - 完整版本（带所有参数）
     */
    @PostMapping("/metrics/advanced")
    public ResponseEntity<ProcessResult> getMetricsAdvanced(
            @RequestBody MetricsRequest request) {
        
        // 构建处理上下文
        ProcessContext context = new ProcessContext()
                .withPageSize(request.getPageSize())
                .withRequiredMetricCodes(request.getMetricCodes())
                .withExcludeOrgCodes(request.getExcludeOrgCodes())
                .withPeriodRange(request.getStartPeriod(), request.getEndPeriod());

        // 添加额外参数
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

        // 执行处理
        ProcessResult result = processor.processData(context);
        
        return ResponseEntity.ok(result);
    }

    /**
     * 请求参数封装
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

### 9. 配置类
```java
package org.example.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 应用配置
 */
@Configuration
@EnableCaching
@EnableAsync
@ComponentScan(basePackages = "org.example")
public class AppConfig {

    // 可以在这里配置其他Bean
}
```

### 10. 单元测试示例
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
 * 策略测试
 */
class FilterStrategyTest {

    @Test
    void testMetricCodeFilterStrategy() {
        // 准备数据
        MetricCodeFilterStrategy strategy = new MetricCodeFilterStrategy();
        
        BidsResp resp = new BidsResp();
        resp.setMetricCode("REVENUE");
        
        ProcessContext context = new ProcessContext();
        Set<String> requiredCodes = new HashSet<>();
        requiredCodes.add("REVENUE");
        requiredCodes.add("PROFIT");
        context.setRequiredMetricCodes(requiredCodes);
        
        FilterMetadata metadata = new FilterMetadata();
        
        // 测试
        assertTrue(strategy.isApplicable(context));
        assertTrue(strategy.filter(resp, metadata, context));
        
        // 测试不在列表中的指标
        resp.setMetricCode("COST");
        assertFalse(strategy.filter(resp, metadata, context));
    }
}
```

## 方案总结

### 核心优势

1. **策略模式的完整实现**
   - 定义了清晰的策略接口（FilterStrategy、TransformStrategy）
   - 每个策略职责单一，易于扩展
   - 通过StrategyManager统一管理和调度
   - 支持策略优先级和动态激活

2. **建造者模式的优雅应用**
   - MetricMeasureBuilder实现线程安全的构建
   - 支持链式调用和批量操作
   - 提供合并、清空等高级功能
   - 防止重复构建

3. **流式处理的高效实现**
   - 使用Stream API进行数据处理
   - 并行流提升处理速度
   - 减少中间对象创建

4. **架构清晰**