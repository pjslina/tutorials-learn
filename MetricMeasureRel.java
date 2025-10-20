package com.example.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 指标度量关联关系实体
 */
@Data
public class MetricMeasureRel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long relId;
    private Long metricId;
    private Long measureId;
    private String relationType;
    private String isRequired;
    private Integer displayOrder;
    private String calculationRule;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String remark;
}