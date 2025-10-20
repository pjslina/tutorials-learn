package com.example.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 度量元数据实体
 */
@Data
public class Measure implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long measureId;
    private String measureCode;
    private String measureName;
    private String measureNameEn;
    private String measureType;
    private String dataFormat;
    private Integer decimalPlaces;
    private String currency;
    private String unit;
    private String description;
    private String isKeyMeasure;
    private Integer sortOrder;
    private String status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String remark;

    // 关联信息（来自关联表）
    private String relationType;
    private String isRequired;
    private Integer displayOrder;
    private String calculationRule;
}