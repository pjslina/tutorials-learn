package com.panjin.boot.jdbi.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author panjin
 */
@Builder
@Data
public class CarModel {
    private Long id;
    private String name;
    private Integer year;
    private String sku;
    private Long makerId;
}
