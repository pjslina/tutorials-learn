package org.example.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Ԫ���ݷ��� - ������غͻ���Ԫ����
 */
@Service
public class MetadataService {

    /**
     * ���ع��������Ԫ����
     * һ���Լ��أ������̹߳���
     */
    public OptimizedBidsDataProcessor.FilterMetadata loadFilterMetadata(
            OptimizedBidsDataProcessor.ProcessContext context) {
        
        OptimizedBidsDataProcessor.FilterMetadata metadata = 
                new OptimizedBidsDataProcessor.FilterMetadata();

        // TODO: �����ݿ�򻺴����Ԫ����
        // 1. ����ָ��ӳ���ϵ
        metadata.setMetricMapping(loadMetricMapping());

        // 2. ������Ч�������
        metadata.setValidDomainCodes(loadValidDomainCodes());

        // 3. ������֯�㼶��ϵ
        metadata.setOrgHierarchy(loadOrgHierarchy());

        return metadata;
    }

    private Map<String, String> loadMetricMapping() {
        // TODO: ʵ��
        return new HashMap<>();
    }

    private Set<String> loadValidDomainCodes() {
        // TODO: ʵ��
        return new HashSet<>();
    }

    private Map<String, List<String>> loadOrgHierarchy() {
        // TODO: ʵ��
        return new HashMap<>();
    }
}
```

## �Ż������ĺ�������

### 1. **�����м����MiddleData**
- ֱ�Ӵ�BidsRespת����MetricMeasure
- ����50%�Ķ��󴴽����ڴ�ռ��
- ����һ�����������ݱ���

### 2. **��ʽ����**
```
BidsResp �� (����) �� (ת��Measure) �� (ֱ�Ӿۺϵ����)