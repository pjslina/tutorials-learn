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
 * ָ���������ʵ��
 */
@Slf4j
@Service
public class MetricMeasureServiceImpl implements MetricMeasureService {

    @Autowired
    private MetricMeasureMapper metricMeasureMapper;

    @Override
    @Cacheable(value = "metricsWithMeasures", key = "#metricIds?.toString() ?: 'all'")
    public List<Metric> getMetricsWithMeasures(List<Long> metricIds) {
        log.info("��ѯָ�꼰�������ָ��IDs: {}", metricIds);
        List<Metric> metrics = metricMeasureMapper.selectMetricsWithMeasures(metricIds, "1");
        log.info("��ѯ�� {} ��ָ��", metrics.size());
        return metrics;
    }

    @Override
    @Cacheable(value = "metricWithMeasures", key = "#metricId")
    public Metric getMetricWithMeasuresById(Long metricId) {
        log.info("��ѯָ�꼰�������ָ��ID: {}", metricId);
        Metric metric = metricMeasureMapper.selectMetricWithMeasuresById(metricId);
        if (metric != null) {
            log.info("��ѯ��ָ��: {}, ���� {} ������", metric.getMetricName(), 
                    metric.getMeasures() != null ? metric.getMeasures().size() : 0);
        } else {
            log.warn("δ�ҵ�ָ��ID: {}", metricId);
        }
        return metric;
    }

    @Override
    @Cacheable(value = "metricMeasureFlat", 
               key = "#metricIds?.toString() + '_' + #measureIds?.toString()")
    public List<MetricMeasureFlatDTO> getMetricMeasureFlat(List<Long> metricIds, List<Long> measureIds) {
        log.info("��ѯָ�����ƽ�����ݣ�ָ��IDs: {}, ����IDs: {}", metricIds, measureIds);
        List<MetricMeasureFlatDTO> flatData = metricMeasureMapper.selectMetricMeasureFlat(
                metricIds, measureIds, "1");
        log.info("��ѯ�� {} ��ƽ������", flatData.size());
        return flatData;
    }

    @Override
    @Cacheable(value = "metricsByType", key = "#metricType")
    public List<Metric> getMetricsByType(String metricType) {
        log.info("�������Ͳ�ѯָ��: {}", metricType);
        List<Metric> metrics = metricMeasureMapper.selectMetricsByType(metricType);
        log.info("��ѯ�� {} �� {} ���͵�ָ��", metrics.size(), metricType);
        return metrics;
    }

    @Override
    @Cacheable(value = "flatByMeasureType", key = "#measureType")
    public List<MetricMeasureFlatDTO> getFlatByMeasureType(String measureType) {
        log.info("���ݶ������Ͳ�ѯƽ������: {}", measureType);
        List<MetricMeasureFlatDTO> flatData = metricMeasureMapper.selectFlatByMeasureType(measureType);
        log.info("��ѯ�� {} �� {} ���͵�ƽ������", flatData.size(), measureType);
        return flatData;
    }

    @Override
    @Cacheable(value = "metricsWithKeyMeasures")
    public List<Metric> getMetricsWithKeyMeasures() {
        log.info("��ѯ�����ؼ�������ָ��");
        List<Metric> metrics = metricMeasureMapper.selectMetricsWithKeyMeasures();
        log.info("��ѯ�� {} �������ؼ�������ָ��", metrics.size());
        return metrics;
    }

    @Override
    @Cacheable(value = "allActiveMetrics")
    public List<Metric> getAllActiveMetrics() {
        log.info("��ѯ�������õ�ָ��");
        return metricMeasureMapper.selectMetricsWithMeasures(null, "1");
    }

    @Override
    @Cacheable(value = "allActiveFlatData")
    public List<MetricMeasureFlatDTO> getAllActiveFlatData() {
        log.info("��ѯ�������õ�ƽ������");
        return metricMeasureMapper.selectMetricMeasureFlat(null, null, "1");
    }
}