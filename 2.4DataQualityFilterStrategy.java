package org.example.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * 数据质量过滤策略
 */
@Slf4j
@Component
public class DataQualityFilterStrategy implements FilterStrategy {

    @Override
    public String getName() {
        return "DataQualityFilter";
    }

    @Override
    public int getPriority() {
        return 10; // 低优先级，最后执行
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return true;
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        // 1. 检查必填字段
        if (resp.getMetricCode() == null || resp.getMetricCode().trim().isEmpty()) {
            log.warn("MetricCode为空，过滤该数据: {}", resp);
            return false;
        }

        if (resp.getMeasureValue() == null || resp.getMeasureValue().trim().isEmpty()) {
            log.warn("MeasureValue为空，过滤该数据: {}", resp);
            return false;
        }

        // 2. 检查数值有效性
        try {
            double value = Double.parseDouble(resp.getMeasureValue());
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                log.warn("MeasureValue不是有效数字，过滤该数据: {}", resp);
                return false;
            }
        } catch (NumberFormatException e) {
            log.warn("MeasureValue格式错误，过滤该数据: {}", resp);
            return false;
        }

        // 3. 业务规则校验
        if (context.getInputParams() != null) {
            Boolean strictMode = (Boolean) context.getInputParams().get("strictMode");
            if (Boolean.TRUE.equals(strictMode)) {
                // 严格模式下的额外校验
                if (resp.getCurrency() == null || resp.getMeasureUnit() == null) {
                    return false;
                }
            }
        }

        return true;
    }
}