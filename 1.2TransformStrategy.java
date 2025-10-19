package org.example.strategy;

import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;

/**
 * ����ת�����Խӿ�
 */
public interface TransformStrategy {
    
    /**
     * ��������
     */
    String getName();
    
    /**
     * �ж��Ƿ�Ӧ�ô˲���
     */
    boolean isApplicable(ProcessContext context);
    
    /**
     * ת��BidsRespΪMeasure
     */
    Measure transform(BidsResp resp, FilterMetadata metadata, ProcessContext context);
    
    /**
     * ת����Ķ��⴦���絥λת������ֵ����ȣ�
     */
    default void postProcess(Measure measure, BidsResp resp, ProcessContext context) {
        // Ĭ�ϲ�������
    }
}