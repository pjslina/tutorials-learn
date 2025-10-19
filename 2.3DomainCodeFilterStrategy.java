package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * 域代码过滤策略
 */
@Component
public class DomainCodeFilterStrategy implements FilterStrategy {

    @Override
    public String getName() {
        return "DomainCodeFilter";
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return true; // 总是应用
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        if (metadata.getValidDomainCodes() == null || 
            metadata.getValidDomainCodes().isEmpty()) {
            return true;
        }
        return metadata.getValidDomainCodes().contains(resp.getDomainCode());
    }
}