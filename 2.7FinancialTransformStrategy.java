package org.example.strategy.impl;

import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.example.strategy.TransformStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ��������ת������
 * ���������ת����
 */
@Component
public class FinancialTransformStrategy implements TransformStrategy {

    @Override
    public String getName() {
        return "FinancialTransform";
    }

    @Override
    public boolean isApplicable(ProcessContext context) {
        return context.getInputParams() != null && 
               Boolean.TRUE.equals(context.getInputParams().get("isFinancial"));
    }

    @Override
    public Measure transform(BidsResp resp, FilterMetadata metadata, ProcessContext context) {
        Measure measure = new Measure();
        measure.setMeasureCode(resp.getMeasureCode());
        measure.setMeasureUnit(resp.getMeasureUnit());
        measure.setCurrency(resp.getCurrency());
        
        // �����������⴦��������λС��
        try {
            BigDecimal value = new BigDecimal(resp.getMeasureValue());
            value = value.setScale(2, RoundingMode.HALF_UP);
            measure.setMeasureValue(value.toString());
        } catch (NumberFormatException e) {
            measure.setMeasureValue(resp.getMeasureValue());
        }
        
        return measure;
    }

    @Override
    public void postProcess(Measure measure, BidsResp resp, ProcessContext context) {
        // ����ת��
        if (context.getInputParams().containsKey("targetCurrency")) {
            String targetCurrency = (String) context.getInputParams().get("targetCurrency");
            if (!targetCurrency.equals(measure.getCurrency())) {
                convertCurrency(measure, targetCurrency, context);
            }
        }
    }

    private void convertCurrency(Measure measure, String targetCurrency, ProcessContext context) {
        // TODO: ʵ�ֻ���ת���߼�
        // ��context��metadata�л�ȡ���ʣ�����ת��
    }
}