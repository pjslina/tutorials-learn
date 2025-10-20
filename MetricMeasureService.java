package com.example.service;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;

import java.util.List;

/**
 * ָ���������ӿ�
 */
public interface MetricMeasureService {

    /**
     * ��ѯָ�꼰������Ķ����б�Ƕ�׽ṹ��
     *
     * @param metricIds ָ��ID�б�
     * @return ָ���б�����������
     */
    List<Metric> getMetricsWithMeasures(List<Long> metricIds);

    /**
     * ��ѯ����ָ�꼰������Ķ���
     *
     * @param metricId ָ��ID
     * @return ָ�꣨���������б�
     */
    Metric getMetricWithMeasuresById(Long metricId);

    /**
     * ��ѯָ�����ƽ������
     *
     * @param metricIds ָ��ID�б�
     * @param measureIds ����ID�б�
     * @return ƽ�������б�
     */
    List<MetricMeasureFlatDTO> getMetricMeasureFlat(List<Long> metricIds, List<Long> measureIds);

    /**
     * ����ָ�����Ͳ�ѯ
     *
     * @param metricType ָ������
     * @return ָ���б�����������
     */
    List<Metric> getMetricsByType(String metricType);

    /**
     * ���ݶ������Ͳ�ѯƽ������
     *
     * @param measureType ��������
     * @return ƽ�������б�
     */
    List<MetricMeasureFlatDTO> getFlatByMeasureType(String measureType);

    /**
     * ��ѯ���йؼ�������ָ��
     *
     * @return ָ���б��������ؼ�������
     */
    List<Metric> getMetricsWithKeyMeasures();

    /**
     * ��ѯ�������õ�ָ��
     *
     * @return ָ���б�����������
     */
    List<Metric> getAllActiveMetrics();

    /**
     * ��ѯ�������õ�ƽ������
     *
     * @return ƽ�������б�
     */
    List<MetricMeasureFlatDTO> getAllActiveFlatData();
}