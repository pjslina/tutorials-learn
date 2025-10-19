package org.example.service;

import org.example.dto.BidsResp;
import org.springframework.stereotype.Service;

/**
 * 过滤服务 - 封装复杂的过滤逻辑
 */
@Service
public class BidsFilterService {

    /**
     * 判断BidsResp是否应该被包含
     * 这里集中处理所有复杂的过滤规则
     */
    public boolean shouldInclude(
            BidsResp resp,
            OptimizedBidsDataProcessor.FilterMetadata metadata,
            OptimizedBidsDataProcessor.ProcessContext context) {

        // 1. 基于入参的过滤
        if (context.getRequiredMetricCodes() != null && 
            !context.getRequiredMetricCodes().isEmpty()) {
            if (!context.getRequiredMetricCodes().contains(resp.getMetricCode())) {
                return false;
            }
        }

        // 2. 基于元数据的过滤
        if (metadata.getValidDomainCodes() != null) {
            if (!metadata.getValidDomainCodes().contains(resp.getDomainCode())) {
                return false;
            }
        }

        // 3. 排除特定组织
        if (context.getExcludeOrgCodes() != null && 
            context.getExcludeOrgCodes().contains(resp.getOrgCode())) {
            return false;
        }

        // 4. 复杂的业务规则
        if (!validateBusinessRules(resp, metadata, context)) {
            return false;
        }

        // 5. 数据质量检查
        if (!isDataValid(resp)) {
            return false;
        }

        return true;
    }

    /**
     * 业务规则验证
     */
    private boolean validateBusinessRules(
            BidsResp resp,
            OptimizedBidsDataProcessor.FilterMetadata metadata,
            OptimizedBidsDataProcessor.ProcessContext context) {
        
        // TODO: 实现具体的业务规则
        // 例如：组织层级检查、指标有效性检查等
        
        return true;
    }

    /**
     * 数据有效性检查
     */
    private boolean isDataValid(BidsResp resp) {
        // 检查必填字段
        if (resp.getMetricCode() == null || resp.getMetricCode().isEmpty()) {
            return false;
        }
        
        if (resp.getMeasureValue() == null || resp.getMeasureValue().isEmpty()) {
            return false;
        }

        // 检查数值有效性
        try {
            Double.parseDouble(resp.getMeasureValue());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}