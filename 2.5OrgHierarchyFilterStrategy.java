package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * 组织层级过滤策略
 * 根据组织层级关系进行复杂过滤
 */
@Component
public class OrgHierarchyFilterStrategy implements FilterStrategy {

    @Override
    public String getName() {
        return "OrgHierarchyFilter";
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return context.getInputParams() != null && 
               context.getInputParams().containsKey("parentOrgCode");
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        String parentOrgCode = (String) context.getInputParams().get("parentOrgCode");
        
        // 检查该组织是否属于指定的父组织层级
        if (metadata.getOrgHierarchy() != null && 
            metadata.getOrgHierarchy().containsKey(parentOrgCode)) {
            
            return metadata.getOrgHierarchy().get(parentOrgCode)
                    .contains(resp.getOrgCode());
        }
        
        return true;
    }
}