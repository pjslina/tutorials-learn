package com.example.service;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;

import java.util.List;

/**
 * 指标度量服务接口
 */
public interface MetricMeasureService {

    /**
     * 查询指标及其关联的度量列表（嵌套结构）
     *
     * @param metricIds 指标ID列表
     * @return 指标列表（包含度量）
     */
    List<Metric> getMetricsWithMeasures(List<Long> metricIds);

    /**
     * 查询单个指标及其关联的度量
     *
     * @param metricId 指标ID
     * @return 指标（包含度量列表）
     */
    Metric getMetricWithMeasuresById(Long metricId);

    /**
     * 查询指标度量平铺数据
     *
     * @param metricIds 指标ID列表
     * @param measureIds 度量ID列表
     * @return 平铺数据列表
     */
    List<MetricMeasureFlatDTO> getMetricMeasureFlat(List<Long> metricIds, List<Long> measureIds);

    /**
     * 根据指标类型查询
     *
     * @param metricType 指标类型
     * @return 指标列表（包含度量）
     */
    List<Metric> getMetricsByType(String metricType);

    /**
     * 根据度量类型查询平铺数据
     *
     * @param measureType 度量类型
     * @return 平铺数据列表
     */
    List<MetricMeasureFlatDTO> getFlatByMeasureType(String measureType);

    /**
     * 查询所有关键度量的指标
     *
     * @return 指标列表（仅包含关键度量）
     */
    List<Metric> getMetricsWithKeyMeasures();

    /**
     * 查询所有启用的指标
     *
     * @return 指标列表（包含度量）
     */
    List<Metric> getAllActiveMetrics();

    /**
     * 查询所有启用的平铺数据
     *
     * @return 平铺数据列表
     */
    List<MetricMeasureFlatDTO> getAllActiveFlatData();
}