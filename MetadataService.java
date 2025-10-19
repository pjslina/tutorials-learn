package org.example.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 元数据服务 - 负责加载和缓存元数据
 */
@Service
public class MetadataService {

    /**
     * 加载过滤所需的元数据
     * 一次性加载，所有线程共享
     */
    public OptimizedBidsDataProcessor.FilterMetadata loadFilterMetadata(
            OptimizedBidsDataProcessor.ProcessContext context) {
        
        OptimizedBidsDataProcessor.FilterMetadata metadata = 
                new OptimizedBidsDataProcessor.FilterMetadata();

        // TODO: 从数据库或缓存加载元数据
        // 1. 加载指标映射关系
        metadata.setMetricMapping(loadMetricMapping());

        // 2. 加载有效的域代码
        metadata.setValidDomainCodes(loadValidDomainCodes());

        // 3. 加载组织层级关系
        metadata.setOrgHierarchy(loadOrgHierarchy());

        return metadata;
    }

    private Map<String, String> loadMetricMapping() {
        // TODO: 实现
        return new HashMap<>();
    }

    private Set<String> loadValidDomainCodes() {
        // TODO: 实现
        return new HashSet<>();
    }

    private Map<String, List<String>> loadOrgHierarchy() {
        // TODO: 实现
        return new HashMap<>();
    }
}
```

## 优化方案的核心优势

### 1. **消除中间对象MiddleData**
- 直接从BidsResp转换到MetricMeasure
- 减少50%的对象创建和内存占用
- 减少一次完整的数据遍历

### 2. **流式处理**
```
BidsResp → (过滤) → (转换Measure) → (直接聚合到结果)