package com.example.service;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 指标度量服务测试
 */
@SpringBootTest
class MetricMeasureServiceTest {

    @Autowired
    private MetricMeasureService metricMeasureService;

    @Test
    void testGetMetricsWithMeasures() {
        // 测试查询所有指标
        List<Metric> allMetrics = metricMeasureService.getAllActiveMetrics();
        assertNotNull(allMetrics);
        assertFalse(allMetrics.isEmpty());
        
        System.out.println("=== 查询所有指标及其度量（嵌套结构） ===");
        allMetrics.forEach(metric -> {
            System.out.println("指标: " + metric.getMetricName() + " (" + metric.getMetricCode() + ")");
            if (metric.getMeasures() != null && !metric.getMeasures().isEmpty()) {
                metric.getMeasures().forEach(measure -> {
                    System.out.println("  - 度量: " + measure.getMeasureName() + 
                            " (" + measure.getMeasureCode() + ")" +
                            " [关联类型: " + measure.getRelationType() + "]");
                });
            }
            System.out.println();
        });
    }

    @Test
    void testGetMetricWithMeasuresById() {
        // 测试查询单个指标
        Long metricId = 1001L;
        Metric metric = metricMeasureService.getMetricWithMeasuresById(metricId);
        
        assertNotNull(metric);
        assertEquals(metricId, metric.getMetricId());
        assertNotNull(metric.getMeasures());
        
        System.out.println("=== 查询指标详情 ===");
        System.out.println("指标ID: " + metric.getMetricId());
        System.out.println("指标名称: " + metric.getMetricName());
        System.out.println("指标类型: " + metric.getMetricType());
        System.out.println("关联度量数: " + metric.getMeasures().size());
        System.out.println("度量列表:");
        metric.getMeasures().forEach(measure -> {
            System.out.println("  - " + measure.getMeasureName() + 
                    " (顺序: " + measure.getDisplayOrder() + ")");
        });
    }

    @Test
    void testGetMetricMeasureFlat() {
        // 测试查询平铺数据
        List<Long> metricIds = Arrays.asList(1001L, 1002L);
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getMetricMeasureFlat(metricIds, null);
        
        assertNotNull(flatData);
        assertFalse(flatData.isEmpty());
        
        System.out.println("=== 查询指标度量平铺数据 ===");
        System.out.println("查询条件 - 指标IDs: " + metricIds);
        System.out.println("查询结果数: " + flatData.size());
        System.out.println();
        
        flatData.forEach(dto -> {
            System.out.println("指标: " + dto.getMetricName() + " | 度量: " + dto.getMeasureName() + 
                    " | 类型: " + dto.getRelationType() + " | 顺序: " + dto.getDisplayOrder());
        });
    }

    @Test
    void testGetMetricsByType() {
        // 测试按类型查询
        String metricType = "BASIC";
        List<Metric> metrics = metricMeasureService.getMetricsByType(metricType);
        
        assertNotNull(metrics);
        
        System.out.println("=== 按类型查询指标: " + metricType + " ===");
        metrics.forEach(metric -> {
            System.out.println("指标: " + metric.getMetricName() + 
                    " (类型: " + metric.getMetricType() + 
                    ", 度量数: " + (metric.getMeasures() != null ? metric.getMeasures().size() : 0) + ")");
        });
    }

    @Test
    void testGetFlatByMeasureType() {
        // 测试按度量类型查询平铺数据
        String measureType = "VALUE";
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getFlatByMeasureType(measureType);
        
        assertNotNull(flatData);
        
        System.out.println("=== 按度量类型查询平铺数据: " + measureType + " ===");
        flatData.forEach(dto -> {
            System.out.println("指标: " + dto.getMetricName() + " | 度量: " + dto.getMeasureName() + 
                    " (类型: " + dto.getMeasureType() + ")");
        });
    }

    @Test
    void testGetMetricsWithKeyMeasures() {
        // 测试查询包含关键度量的指标
        List<Metric> metrics = metricMeasureService.getMetricsWithKeyMeasures();
        
        assertNotNull(metrics);
        
        System.out.println("=== 包含关键度量的指标 ===");
        metrics.forEach(metric -> {
            System.out.println("指标: " + metric.getMetricName());
            metric.getMeasures().forEach(measure -> {
                if ("1".equals(measure.getIsKeyMeasure())) {
                    System.out.println("  [关键度量] " + measure.getMeasureName());
                }
            });
        });
    }
}