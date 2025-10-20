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
 * ָ������������
 */
@SpringBootTest
class MetricMeasureServiceTest {

    @Autowired
    private MetricMeasureService metricMeasureService;

    @Test
    void testGetMetricsWithMeasures() {
        // ���Բ�ѯ����ָ��
        List<Metric> allMetrics = metricMeasureService.getAllActiveMetrics();
        assertNotNull(allMetrics);
        assertFalse(allMetrics.isEmpty());
        
        System.out.println("=== ��ѯ����ָ�꼰�������Ƕ�׽ṹ�� ===");
        allMetrics.forEach(metric -> {
            System.out.println("ָ��: " + metric.getMetricName() + " (" + metric.getMetricCode() + ")");
            if (metric.getMeasures() != null && !metric.getMeasures().isEmpty()) {
                metric.getMeasures().forEach(measure -> {
                    System.out.println("  - ����: " + measure.getMeasureName() + 
                            " (" + measure.getMeasureCode() + ")" +
                            " [��������: " + measure.getRelationType() + "]");
                });
            }
            System.out.println();
        });
    }

    @Test
    void testGetMetricWithMeasuresById() {
        // ���Բ�ѯ����ָ��
        Long metricId = 1001L;
        Metric metric = metricMeasureService.getMetricWithMeasuresById(metricId);
        
        assertNotNull(metric);
        assertEquals(metricId, metric.getMetricId());
        assertNotNull(metric.getMeasures());
        
        System.out.println("=== ��ѯָ������ ===");
        System.out.println("ָ��ID: " + metric.getMetricId());
        System.out.println("ָ������: " + metric.getMetricName());
        System.out.println("ָ������: " + metric.getMetricType());
        System.out.println("����������: " + metric.getMeasures().size());
        System.out.println("�����б�:");
        metric.getMeasures().forEach(measure -> {
            System.out.println("  - " + measure.getMeasureName() + 
                    " (˳��: " + measure.getDisplayOrder() + ")");
        });
    }

    @Test
    void testGetMetricMeasureFlat() {
        // ���Բ�ѯƽ������
        List<Long> metricIds = Arrays.asList(1001L, 1002L);
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getMetricMeasureFlat(metricIds, null);
        
        assertNotNull(flatData);
        assertFalse(flatData.isEmpty());
        
        System.out.println("=== ��ѯָ�����ƽ������ ===");
        System.out.println("��ѯ���� - ָ��IDs: " + metricIds);
        System.out.println("��ѯ�����: " + flatData.size());
        System.out.println();
        
        flatData.forEach(dto -> {
            System.out.println("ָ��: " + dto.getMetricName() + " | ����: " + dto.getMeasureName() + 
                    " | ����: " + dto.getRelationType() + " | ˳��: " + dto.getDisplayOrder());
        });
    }

    @Test
    void testGetMetricsByType() {
        // ���԰����Ͳ�ѯ
        String metricType = "BASIC";
        List<Metric> metrics = metricMeasureService.getMetricsByType(metricType);
        
        assertNotNull(metrics);
        
        System.out.println("=== �����Ͳ�ѯָ��: " + metricType + " ===");
        metrics.forEach(metric -> {
            System.out.println("ָ��: " + metric.getMetricName() + 
                    " (����: " + metric.getMetricType() + 
                    ", ������: " + (metric.getMeasures() != null ? metric.getMeasures().size() : 0) + ")");
        });
    }

    @Test
    void testGetFlatByMeasureType() {
        // ���԰��������Ͳ�ѯƽ������
        String measureType = "VALUE";
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getFlatByMeasureType(measureType);
        
        assertNotNull(flatData);
        
        System.out.println("=== ���������Ͳ�ѯƽ������: " + measureType + " ===");
        flatData.forEach(dto -> {
            System.out.println("ָ��: " + dto.getMetricName() + " | ����: " + dto.getMeasureName() + 
                    " (����: " + dto.getMeasureType() + ")");
        });
    }

    @Test
    void testGetMetricsWithKeyMeasures() {
        // ���Բ�ѯ�����ؼ�������ָ��
        List<Metric> metrics = metricMeasureService.getMetricsWithKeyMeasures();
        
        assertNotNull(metrics);
        
        System.out.println("=== �����ؼ�������ָ�� ===");
        metrics.forEach(metric -> {
            System.out.println("ָ��: " + metric.getMetricName());
            metric.getMeasures().forEach(measure -> {
                if ("1".equals(measure.getIsKeyMeasure())) {
                    System.out.println("  [�ؼ�����] " + measure.getMeasureName());
                }
            });
        });
    }
}