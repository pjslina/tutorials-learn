package org.example.strategy;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;

/**
 * 过滤策略接口
 */
public interface FilterStrategy {
    
    /**
     * 策略名称
     */
    String getName();
    
    /**
     * 策略优先级（数字越小优先级越高）
     */
    int getPriority();
    
    /**
     * 判断是否应用此策略
     */
    boolean isApplicable(ProcessContext context);
    
    /**
     * 执行过滤逻辑
     */
    boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context);
}