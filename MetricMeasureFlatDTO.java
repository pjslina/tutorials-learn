package com.example.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * 指标度量平铺DTO
 */
@Data
public class MetricMeasureFlatDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // 指标信息
    private Long metricId;
    private String metricCode;
    private String metricName;
    private String metricNameEn;
    private String metricType;
    private String metricCategory;
    private String metricDataType;
    private String metricUnit;
    private String metricDescription;

    // 度量信息
    private Long measureId;
    private String measureCode;
    private String measureName;
    private String measureNameEn;
    private String measureType;
    private String dataFormat;
    private Integer decimalPlaces;
    private String currency;
    private String measureUnit;
    private String measureDescription;
    private String isKeyMeasure;

    // 关联信息
    private String relationType;
    private String isRequired;
    private Integer displayOrder;
    private String calculationRule;
}