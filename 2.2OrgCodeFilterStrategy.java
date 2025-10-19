package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * 组织代码过滤策略
 */
@Component
public class OrgCodeFilterStrategy implements FilterStrategy {

    @Override
    public String getName() {
        return "OrgCodeFilter";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return context.getExcludeOrgCodes() != null && 
               !context.getExcludeOrgCodes().isEmpty();
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        // 在排除列表中，过滤掉
        return !context.getExcludeOrgCodes().contains(resp.getOrgCode());
    }
}