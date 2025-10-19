package org.example.builder;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Measure;
import org.example.dto.MetricMeasure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MetricMeasure构建器 - 线程安全的建造者模式实现
 */
@Slf4j
public class MetricMeasureBuilder {

    private final String periodId;
    private final ConcurrentHashMap<String, List<Measure>> metricMap;
    private volatile boolean built = false;

    private MetricMeasureBuilder(String periodId) {
        this.periodId = periodId;
        this.metricMap = new ConcurrentHashMap<>();
    }

    /**
     * 创建Builder实例
     */
    public static MetricMeasureBuilder create(String periodId) {
        return new MetricMeasureBuilder(periodId);
    }

    /**
     * 添加单个Measure
     */
    public MetricMeasureBuilder addMeasure(String metricKey, Measure measure) {
        if (built) {
            throw new IllegalStateException("Builder已经构建完成，不能再添加数据");
        }

        metricMap.computeIfAbsent(metricKey, k -> new CopyOnWriteArrayList<>())
                 .add(measure);
        return this;
    }

    /**
     * 批量添加Measures
     */
    public MetricMeasureBuilder addMeasures(String metricKey, List<Measure> measures) {
        if (built) {
            throw new IllegalStateException("Builder已经构建完成，不能再添加数据");
        }

        if (measures != null && !measures.isEmpty()) {
            metricMap.computeIfAbsent(metricKey, k -> new CopyOnWriteArrayList<>())
                     .addAll(measures);
        }
        return this;
    }

    /**
     * 合并另一个Builder的数据
     */
    public MetricMeasureBuilder merge(MetricMeasureBuilder other) {
        if (built) {
            throw new IllegalStateException("Builder已经构建完成，不能再添加数据");
        }

        if (!this.periodId.equals(other.periodId)) {
            throw new IllegalArgumentException("不能合并不同period的Builder");
        }

        other.metricMap.forEach((key, measures) -> {
            metricMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>())
                     .addAll(measures);
        });

        return this;
    }

    /**
     * 获取当前数据量
     */
    public int size() {
        return metricMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    /**
     * 构建最终的MetricMeasure对象
     */
    public MetricMeasure build() {
        if (built) {
            throw new IllegalStateException("Builder已经构建过了，不能重复构建");
        }

        built = true;

        MetricMeasure result = new MetricMeasure();
        result.setPeriodId(periodId);

        // 转换为普通HashMap（不再需要并发访问）
        Map<String, List<Measure>> finalMap = new HashMap<>(metricMap.size());
        
        metricMap.forEach((key, measures) -> {
            // 去重、排序等后处理
            List<Measure> processedMeasures = postProcessMeasures(measures);
            finalMap.put(key, processedMeasures);
        });

        result.setMetricMap(finalMap);

        log.debug("构建MetricMeasure完成，periodId: {}, 包含{}个metricKey, 共{}条Measure",
                periodId, finalMap.size(), size());

        return result;
    }

    /**
     * 对Measure列表进行后处理
     */
    private List<Measure> postProcessMeasures(List<Measure> measures) {
        if (measures == null || measures.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. 去重（根据measureCode）
        Map<String, Measure> uniqueMap = new LinkedHashMap<>();
        for (Measure measure : measures) {
            String key = measure.getMeasureCode();
            // 如果有重复，保留最后一个
            uniqueMap.put(key, measure);
        }

        // 2. 排序（按measureCode）
        List<Measure> result = new ArrayList<>(uniqueMap.values());
        result.sort(Comparator.comparing(Measure::getMeasureCode));

        return result;
    }

    /**
     * 清空数据（用于重用Builder）
     */
    public void clear() {
        metricMap.clear();
        built = false;
    }
}