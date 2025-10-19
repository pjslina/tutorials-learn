package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * ��֯�㼶���˲���
 * ������֯�㼶��ϵ���и��ӹ���
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
        
        // ������֯�Ƿ�����ָ���ĸ���֯�㼶
        if (metadata.getOrgHierarchy() != null && 
            metadata.getOrgHierarchy().containsKey(parentOrgCode)) {
            
            return metadata.getOrgHierarchy().get(parentOrgCode)
                    .contains(resp.getOrgCode());
        }
        
        return true;
    }
}