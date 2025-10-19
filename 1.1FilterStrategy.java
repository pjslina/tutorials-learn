package org.example.strategy;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;

/**
 * ���˲��Խӿ�
 */
public interface FilterStrategy {
    
    /**
     * ��������
     */
    String getName();
    
    /**
     * �������ȼ�������ԽС���ȼ�Խ�ߣ�
     */
    int getPriority();
    
    /**
     * �ж��Ƿ�Ӧ�ô˲���
     */
    boolean isApplicable(ProcessContext context);
    
    /**
     * ִ�й����߼�
     */
    boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context);
}