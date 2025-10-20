package com.example.mapper;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ָ�����Mapper�ӿ�
 */
@Mapper
public interface MetricMeasureMapper {

    /**
     * ��ѯָ�꼰������Ķ����б�Ƕ�׽ṹ��
     * �����List<Metric>��ÿ��Metric����List<Measure>
     *
     * @param metricIds ָ��ID�б�Ϊ�����ѯȫ��
     * @param status ״̬���ˣ�Ϊ���򲻹���
     * @return ָ���б�����������
     */
    List<Metric> selectMetricsWithMeasures(@Param("metricIds") List<Long> metricIds,
                                           @Param("status") String status);

    /**
     * ��ѯ����ָ�꼰������Ķ����б�
     *
     * @param metricId ָ��ID
     * @return ָ�꣨���������б�
     */
    Metric selectMetricWithMeasuresById(@Param("metricId") Long metricId);

    /**
     * ��ѯָ�����ƽ������
     * �����ָ��+����ƽ�̣�һ����¼����ָ��Ͷ����������ֶ�
     *
     * @param metricIds ָ��ID�б�Ϊ�����ѯȫ��
     * @param measureIds ����ID�б�Ϊ�����ѯȫ��
     * @param status ״̬���ˣ�Ϊ���򲻹���
     * @return ƽ�������б�
     */
    List<MetricMeasureFlatDTO> selectMetricMeasureFlat(@Param("metricIds") List<Long> metricIds,
                                                        @Param("measureIds") List<Long> measureIds,
                                                        @Param("status") String status);

    /**
     * ����ָ�����Ͳ�ѯ
     *
     * @param metricType ָ������
     * @return ָ���б�����������
     */
    List<Metric> selectMetricsByType(@Param("metricType") String metricType);

    /**
     * ���ݶ������Ͳ�ѯƽ������
     *
     * @param measureType ��������
     * @return ƽ�������б�
     */
    List<MetricMeasureFlatDTO> selectFlatByMeasureType(@Param("measureType") String measureType);

    /**
     * ��ѯ�ؼ�������ָ��
     *
     * @return ָ���б��������ؼ�������
     */
    List<Metric> selectMetricsWithKeyMeasures();
}