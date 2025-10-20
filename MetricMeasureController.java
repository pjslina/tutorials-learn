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
 * ָ�����������
 */
@Slf4j
@RestController
@RequestMapping("/api/metric-measure")
@Api(tags = "ָ���������")
public class MetricMeasureController {

    @Autowired
    private MetricMeasureService metricMeasureService;

    /**
     * ��ѯָ�꼰������Ķ����б�Ƕ�׽ṹ��
     */
    @PostMapping("/metrics/with-measures")
    @ApiOperation("��ѯָ�꼰�������Ƕ�׽ṹ��")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsWithMeasures(
            @ApiParam("ָ��ID�б�") @RequestBody(required = false) List<Long> metricIds) {
        
        log.info("�������󣺲�ѯָ�꼰�������IDs: {}", metricIds);
        List<Metric> metrics = metricMeasureService.getMetricsWithMeasures(metricIds);
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * ��ѯ����ָ�꼰�����
     */
    @GetMapping("/metrics/{metricId}/with-measures")
    @ApiOperation("��ѯ����ָ�꼰�����")
    public ResponseEntity<ApiResponse<Metric>> getMetricWithMeasures(
            @ApiParam("ָ��ID") @PathVariable Long metricId) {
        
        log.info("�������󣺲�ѯָ�����飬ID: {}", metricId);
        Metric metric = metricMeasureService.getMetricWithMeasuresById(metricId);
        
        if (metric == null) {
            return ResponseEntity.ok(ApiResponse.error("ָ�겻����"));
        }
        
        return ResponseEntity.ok(ApiResponse.success(metric));
    }

    /**
     * ��ѯָ�����ƽ������
     */
    @PostMapping("/flat-data")
    @ApiOperation("��ѯָ�����ƽ������")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getMetricMeasureFlat(
            @RequestBody FlatDataRequest request) {
        
        log.info("�������󣺲�ѯƽ�����ݣ�ָ��IDs: {}, ����IDs: {}", 
                request.getMetricIds(), request.getMeasureIds());
        
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getMetricMeasureFlat(
                request.getMetricIds(), request.getMeasureIds());
        
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * ����ָ�����Ͳ�ѯ
     */
    @GetMapping("/metrics/by-type/{metricType}")
    @ApiOperation("�������Ͳ�ѯָ��")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsByType(
            @ApiParam("ָ������") @PathVariable String metricType) {
        
        log.info("�������󣺸������Ͳ�ѯָ�꣬����: {}", metricType);
        List<Metric> metrics = metricMeasureService.getMetricsByType(metricType);
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * ���ݶ������Ͳ�ѯƽ������
     */
    @GetMapping("/flat-data/by-measure-type/{measureType}")
    @ApiOperation("���ݶ������Ͳ�ѯƽ������")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getFlatByMeasureType(
            @ApiParam("��������") @PathVariable String measureType) {
        
        log.info("�������󣺸��ݶ������Ͳ�ѯƽ�����ݣ�����: {}", measureType);
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getFlatByMeasureType(measureType);
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * ��ѯ�����ؼ�������ָ��
     */
    @GetMapping("/metrics/with-key-measures")
    @ApiOperation("��ѯ�����ؼ�������ָ��")
    public ResponseEntity<ApiResponse<List<Metric>>> getMetricsWithKeyMeasures() {
        log.info("�������󣺲�ѯ�����ؼ�������ָ��");
        List<Metric> metrics = metricMeasureService.getMetricsWithKeyMeasures();
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * ��ѯ�������õ�ָ��
     */
    @GetMapping("/metrics/active")
    @ApiOperation("��ѯ�������õ�ָ��")
    public ResponseEntity<ApiResponse<List<Metric>>> getAllActiveMetrics() {
        log.info("�������󣺲�ѯ�������õ�ָ��");
        List<Metric> metrics = metricMeasureService.getAllActiveMetrics();
        return ResponseEntity.ok(ApiResponse.success(metrics));
    }

    /**
     * ��ѯ�������õ�ƽ������
     */
    @GetMapping("/flat-data/active")
    @ApiOperation("��ѯ�������õ�ƽ������")
    public ResponseEntity<ApiResponse<List<MetricMeasureFlatDTO>>> getAllActiveFlatData() {
        log.info("�������󣺲�ѯ�������õ�ƽ������");
        List<MetricMeasureFlatDTO> flatData = metricMeasureService.getAllActiveFlatData();
        return ResponseEntity.ok(ApiResponse.success(flatData));
    }

    /**
     * ƽ�����ݲ�ѯ����
     */
    @lombok.Data
    public static class FlatDataRequest {
        private List<Long> metricIds;
        private List<Long> measureIds;
    }

    /**
     * ͳһ��Ӧ��װ
     */
    @lombok.Data
    @lombok.AllArgsConstructor
    public static class ApiResponse<T> {
        private boolean success;
        private String message;
        private T data;

        public static <T> ApiResponse<T> success(T data) {
            return new ApiResponse<>(true, "�����ɹ�", data);
        }

        public static <T> ApiResponse<T> error(String message) {
            return new ApiResponse<>(false, message, null);
        }
    }
}