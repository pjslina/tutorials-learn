package com.example.mapper;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 指标度量Mapper接口
 */
@Mapper
public interface MetricMeasureMapper {

    /**
     * 查询指标及其关联的度量列表（嵌套结构）
     * 结果：List<Metric>，每个Metric包含List<Measure>
     *
     * @param metricIds 指标ID列表，为空则查询全部
     * @param status 状态过滤，为空则不过滤
     * @return 指标列表（包含度量）
     */
    List<Metric> selectMetricsWithMeasures(@Param("metricIds") List<Long> metricIds,
                                           @Param("status") String status);

    /**
     * 查询单个指标及其关联的度量列表
     *
     * @param metricId 指标ID
     * @return 指标（包含度量列表）
     */
    Metric selectMetricWithMeasuresById(@Param("metricId") Long metricId);

    /**
     * 查询指标度量平铺数据
     * 结果：指标+度量平铺，一条记录包含指标和度量的所有字段
     *
     * @param metricIds 指标ID列表，为空则查询全部
     * @param measureIds 度量ID列表，为空则查询全部
     * @param status 状态过滤，为空则不过滤
     * @return 平铺数据列表
     */
    List<MetricMeasureFlatDTO> selectMetricMeasureFlat(@Param("metricIds") List<Long> metricIds,
                                                        @Param("measureIds") List<Long> measureIds,
                                                        @Param("status") String status);

    /**
     * 根据指标类型查询
     *
     * @param metricType 指标类型
     * @return 指标列表（包含度量）
     */
    List<Metric> selectMetricsByType(@Param("metricType") String metricType);

    /**
     * 根据度量类型查询平铺数据
     *
     * @param measureType 度量类型
     * @return 平铺数据列表
     */
    List<MetricMeasureFlatDTO> selectFlatByMeasureType(@Param("measureType") String measureType);

    /**
     * 查询关键度量的指标
     *
     * @return 指标列表（仅包含关键度量）
     */
    List<Metric> selectMetricsWithKeyMeasures();
}