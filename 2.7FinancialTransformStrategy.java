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
 * 财务数据转换策略
 * 处理金额、汇率转换等
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
        
        // 财务数据特殊处理：保留两位小数
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
        // 汇率转换
        if (context.getInputParams().containsKey("targetCurrency")) {
            String targetCurrency = (String) context.getInputParams().get("targetCurrency");
            if (!targetCurrency.equals(measure.getCurrency())) {
                convertCurrency(measure, targetCurrency, context);
            }
        }
    }

    private void convertCurrency(Measure measure, String targetCurrency, ProcessContext context) {
        // TODO: 实现汇率转换逻辑
        // 从context或metadata中获取汇率，进行转换
    }
}