package com.example.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * ָ�����ƽ��DTO
 */
@Data
public class MetricMeasureFlatDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    // ָ����Ϣ
    private Long metricId;
    private String metricCode;
    private String metricName;
    private String metricNameEn;
    private String metricType;
    private String metricCategory;
    private String metricDataType;
    private String metricUnit;
    private String metricDescription;

    // ������Ϣ
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

    // ������Ϣ
    private String relationType;
    private String isRequired;
    private Integer displayOrder;
    private String calculationRule;
}