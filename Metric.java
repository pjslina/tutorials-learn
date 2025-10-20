package com.example.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ָ��Ԫ����ʵ��
 */
@Data
public class Metric implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long metricId;
    private String metricCode;
    private String metricName;
    private String metricNameEn;
    private String metricType;
    private String metricCategory;
    private String dataType;
    private String unit;
    private String description;
    private String formula;
    private Integer sortOrder;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String remark;

    // �����Ķ����б�һ�Զࣩ
    private List<Measure> measures;
}