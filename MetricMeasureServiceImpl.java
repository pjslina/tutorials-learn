package com.example.service.impl;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;
import com.example.mapper.MetricMeasureMapper;
import com.example.service.MetricMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 指标度量服务实现
 */
@Slf4j
@Service
public class MetricMeasureServiceImpl implements MetricMeasureService {

    @Autowired
    private MetricMeasureMapper metricMeasureMapper;

    @Override
    @Cacheable(value = "metricsWithMeasures", key = "#metricIds?.toString() ?: 'all'")
    public List<Metric> getMetricsWithMeasures(List<Long> metricIds) {
        log.info("查询指标及其度量，指标IDs: {}", metricIds);
        List<Metric> metrics = metricMeasureMapper.selectMetricsWithMeasures(metricIds, "1");
        log.info("查询到 {} 个指标", metrics.size());
        return metrics;
    }

    @Override
    @Cacheable(value = "metricWithMeasures", key = "#metricId")
    public Metric getMetricWithMeasuresById(Long metricId) {
        log.info("查询指标及其度量，指标ID: {}", metricId);
        Metric metric = metricMeasureMapper.selectMetricWithMeasuresById(metricId);
        if (metric != null) {
            log.info("查询到指标: {}, 包含 {} 个度量", metric.getMetricName(), 
                    metric.getMeasures() != null ? metric.getMeasures().size() : 0);
        } else {
            log.warn("未找到指标ID: {}", metricId);
        }
        return metric;
    }

    @Override
    @Cacheable(value = "metricMeasureFlat", 
               key = "#metricIds?.toString() + '_' + #measureIds?.toString()")
    public List<MetricMeasureFlatDTO> getMetricMeasureFlat(List<Long> metricIds, List<Long> measureIds) {
        log.info("查询指标度量平铺数据，指标IDs: {}, 度量IDs: {}", metricIds, measureIds);
        List<MetricMeasureFlatDTO> flatData = metricMeasureMapper.selectMetricMeasureFlat(
                metricIds, measureIds, "1");
        log.info("查询到 {} 条平铺数据", flatData.size());
        return flatData;
    }

    @Override
    @Cacheable(value = "metricsByType", key = "#metricType")
    public List<Metric> getMetricsByType(String metricType) {
        log.info("根据类型查询指标: {}", metricType);
        List<Metric> metrics = metricMeasureMapper.selectMetricsByType(metricType);
        log.info("查询到 {} 个 {} 类型的指标", metrics.size(), metricType);
        return metrics;
    }

    @Override
    @Cacheable(value = "flatByMeasureType", key = "#measureType")
    public List<MetricMeasureFlatDTO> getFlatByMeasureType(String measureType) {
        log.info("根据度量类型查询平铺数据: {}", measureType);
        List<MetricMeasureFlatDTO> flatData = metricMeasureMapper.selectFlatByMeasureType(measureType);
        log.info("查询到 {} 条 {} 类型的平铺数据", flatData.size(), measureType);
        return flatData;
    }

    @Override
    @Cacheable(value = "metricsWithKeyMeasures")
    public List<Metric> getMetricsWithKeyMeasures() {
        log.info("查询包含关键度量的指标");
        List<Metric> metrics = metricMeasureMapper.selectMetricsWithKeyMeasures();
        log.info("查询到 {} 个包含关键度量的指标", metrics.size());
        return metrics;
    }

    @Override
    @Cacheable(value = "allActiveMetrics")
    public List<Metric> getAllActiveMetrics() {
        log.info("查询所有启用的指标");
        return metricMeasureMapper.selectMetricsWithMeasures(null, "1");
    }

    @Override
    @Cacheable(value = "allActiveFlatData")
    public List<MetricMeasureFlatDTO> getAllActiveFlatData() {
        log.info("查询所有启用的平铺数据");
        return metricMeasureMapper.selectMetricMeasureFlat(null, null, "1");
    }
}