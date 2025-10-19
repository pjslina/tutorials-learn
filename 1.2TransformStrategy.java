package org.example.strategy;

import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;

/**
 * 数据转换策略接口
 */
public interface TransformStrategy {
    
    /**
     * 策略名称
     */
    String getName();
    
    /**
     * 判断是否应用此策略
     */
    boolean isApplicable(ProcessContext context);
    
    /**
     * 转换BidsResp为Measure
     */
    Measure transform(BidsResp resp, FilterMetadata metadata, ProcessContext context);
    
    /**
     * 转换后的额外处理（如单位转换、数值计算等）
     */
    default void postProcess(Measure measure, BidsResp resp, ProcessContext context) {
        // 默认不做处理
    }
}