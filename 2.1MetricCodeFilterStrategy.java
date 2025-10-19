package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * 指标代码过滤策略
 */
@Component
public class MetricCodeFilterStrategy implements FilterStrategy {

    @Override
    public String getName() {
        return "MetricCodeFilter";
    }

    @Override
    public int getPriority() {
        return 1; // 最高优先级
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return context.getRequiredMetricCodes() != null && 
               !context.getRequiredMetricCodes().isEmpty();
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        // 不在需要的指标列表中，过滤掉
        return context.getRequiredMetricCodes().contains(resp.getMetricCode());
    }
}