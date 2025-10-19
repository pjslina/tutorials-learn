package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.TransformStrategy;
import org.springframework.stereotype.Component;

/**
 * ��׼ת������
 */
@Component
public class StandardTransformStrategy implements TransformStrategy {

    @Override
    public String getName() {
        return "StandardTransform";
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return true; // Ĭ�ϲ��ԣ���������
    }

    @Override
    public Measure transform(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        Measure measure = new Measure();
        measure.setMeasureCode(resp.getMeasureCode());
        measure.setMeasureUnit(resp.getMeasureUnit());
        measure.setMeasureValue(resp.getMeasureValue());
        measure.setCurrency(resp.getCurrency());
        return measure;
    }

    @Override
    public void postProcess(Measure measure, BidsResp resp, ProcessContext context) {
        // ��׼ת������Ҫ����
    }
}