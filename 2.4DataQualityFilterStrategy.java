package org.example.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BidsResp;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.FilterStrategy;
import org.springframework.stereotype.Component;

/**
 * �����������˲���
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
        return 10; // �����ȼ������ִ��
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return true;
    }

    @Override
    public boolean filter(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        // 1. �������ֶ�
        if (resp.getMetricCode() == null || resp.getMetricCode().trim().isEmpty()) {
            log.warn("MetricCodeΪ�գ����˸�����: {}", resp);
            return false;
        }

        if (resp.getMeasureValue() == null || resp.getMeasureValue().trim().isEmpty()) {
            log.warn("MeasureValueΪ�գ����˸�����: {}", resp);
            return false;
        }

        // 2. �����ֵ��Ч��
        try {
            double value = Double.parseDouble(resp.getMeasureValue());
            if (Double.isNaN(value) || Double.isInfinite(value)) {
                log.warn("MeasureValue������Ч���֣����˸�����: {}", resp);
                return false;
            }
        } catch (NumberFormatException e) {
            log.warn("MeasureValue��ʽ���󣬹��˸�����: {}", resp);
            return false;
        }

        // 3. ҵ�����У��
        if (context.getInputParams() != null) {
            Boolean strictMode = (Boolean) context.getInputParams().get("strictMode");
            if (Boolean.TRUE.equals(strictMode)) {
                // �ϸ�ģʽ�µĶ���У��
                if (resp.getCurrency() == null || resp.getMeasureUnit() == null) {
                    return false;
                }
            }
        }

        return true;
    }
}