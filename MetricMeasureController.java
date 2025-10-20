package com.example.controller;

import com.example.dto.MetricMeasureFlatDTO;
import com.example.entity.Metric;
import com.example.service.MetricMeasureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 指标度量控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/metric-measure")
@Api(tags = "指标度量管理")
public class MetricMeasureController {

    @Autowired
    private MetricMeasureService metricMeasureService;

    /**
     * 查询指标及其关联的度量列表（嵌套结构）
     */
    @PostMapping("/metrics/with-measures")
    @ApiOperation("查询指标及其度量（嵌套结构）")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsWithMeasures(
            @ApiParam("指标ID列表") @RequestBody(required = false) List<Long> metricIds) {
        
        log.info("接收请求：查询指标及其度量，IDs: {}", metricIds);
        List<Metric> metrics = metricMeasureService.getMetricsWithMeasures(metricIds);
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * 查询单个指标及其度量
     */
    @GetMapping("/metrics/{metricId}/with-measures")
    @ApiOperation("查询单个指标及其度量")
    public ResponseEntity<ApiResponse<Metric>> getMetricWithMeasures(
            @ApiParam("指标ID") @PathVariable Long metricId) {
        
        log.info("接收请求：查询指标详情，ID: {}", metricId);
        Metric metric = metricMeasureService.getMetricWithMeasuresById(metricId);
        
        if (metric == null) {
            return ResponseEntity.ok(ApiResponse.error("指标不存在"));
        }
        
        return ResponseEntity.ok(ApiResponse.success(metric));
    }

    /**
     * 查询指标度量平铺数据
     */
    @PostMapping("/flat-data")
    @ApiOperation("查询指标度量平铺数据")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getMetricMeasureFlat(
            @RequestBody FlatDataRequest request) {
        
        log.info("接收请求：查询平铺数据，指标IDs: {}, 度量IDs: {}", 
                request.getMetricIds(), request.getMeasureIds());
        
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getMetricMeasureFlat(
                request.getMetricIds(), request.getMeasureIds());
        
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * 根据指标类型查询
     */
    @GetMapping("/metrics/by-type/{metricType}")
    @ApiOperation("根据类型查询指标")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsByType(
            @ApiParam("指标类型") @PathVariable String metricType) {
        
        log.info("接收请求：根据类型查询指标，类型: {}", metricType);
        List<Metric> metrics = metricMeasureService.getMetricsByType(metricType);
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * 根据度量类型查询平铺数据
     */
    @GetMapping("/flat-data/by-measure-type/{measureType}")
    @ApiOperation("根据度量类型查询平铺数据")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getFlatByMeasureType(
            @ApiParam("度量类型") @PathVariable String measureType) {
        
        log.info("接收请求：根据度量类型查询平铺数据，类型: {}", measureType);
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getFlatByMeasureType(measureType);
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * 查询包含关键度量的指标
     */
    @GetMapping("/metrics/with-key-measures")
    @ApiOperation("查询包含关键度量的指标")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsWithKeyMeasures() {
        log.info("接收请求：查询包含关键度量的指标");
        List<Metric> metrics = metricMeasureService.getMetricsWithKeyMeasures();
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * 查询所有启用的指标
     */
    @GetMapping("/metrics/active")
    @ApiOperation("查询所有启用的指标")
    public ResponseEntity<ApiResponse<List<Metric>>> getAllActiveMetrics() {
        log.info("接收请求：查询所有启用的指标");
        List<Metric> metrics = metricMeasureService.getAllActiveMetrics();
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * 查询所有启用的平铺数据
     */
    @GetMapping("/flat-data/active")
    @ApiOperation("查询所有启用的平铺数据")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getAllActiveFlatData() {
        log.info("接收请求：查询所有启用的平铺数据");
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getAllActiveFlatData();
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * 平铺数据查询请求
     */
    @lombok.Data
    public static class FlatDataRequest {
        private List<Long> metricIds;
        private List<Long> measureIds;
    }

    /**
     * 统一响应包装
     */
    @lombok.Data
    @lombok.AllArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;

        public static <T> ApiResponse<T> success(T data) {
            return new ApiResponse<>(true, "操作成功", data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }
    }
}