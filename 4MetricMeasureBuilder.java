package org.example.builder;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.Measure;
import org.example.dto.MetricMeasure;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * MetricMeasure������ - �̰߳�ȫ�Ľ�����ģʽʵ��
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
     * ����Builderʵ��
     */
    public static MetricMeasureBuilder create(String periodId) {
        return new MetricMeasureBuilder(periodId);
    }

    /**
     * ��ӵ���Measure
     */
    public MetricMeasureBuilder addMeasure(String metricKey, Measure measure) {
        if (built) {
            throw new IllegalStateException("Builder�Ѿ�������ɣ��������������");
        }

        metricMap.computeIfAbsent(metricKey, k -> new CopyOnWriteArrayList<>())
                 .add(measure);
        return this;
    }

    /**
     * �������Measures
     */
    public MetricMeasureBuilder addMeasures(String metricKey, List<Measure> measures) {
        if (built) {
            throw new IllegalStateException("Builder�Ѿ�������ɣ��������������");
        }

        if (measures != null && !measures.isEmpty()) {
            metricMap.computeIfAbsent(metricKey, k -> new CopyOnWriteArrayList<>())
                     .addAll(measures);
        }
        return this;
    }

    /**
     * �ϲ���һ��Builder������
     */
    public MetricMeasureBuilder merge(MetricMeasureBuilder other) {
        if (built) {
            throw new IllegalStateException("Builder�Ѿ�������ɣ��������������");
        }

        if (!this.periodId.equals(other.periodId)) {
            throw new IllegalArgumentException("���ܺϲ���ͬperiod��Builder");
        }

        other.metricMap.forEach((key, measures) -> {
            metricMap.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>())
                     .addAll(measures);
        });

        return this;
    }

    /**
     * ��ȡ��ǰ������
     */
    public int size() {
        return metricMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    /**
     * �������յ�MetricMeasure����
     */
    public MetricMeasure build() {
        if (built) {
            throw new IllegalStateException("Builder�Ѿ��������ˣ������ظ�����");
        }

        built = true;

        MetricMeasure result = new MetricMeasure();
        result.setPeriodId(periodId);

        // ת��Ϊ��ͨHashMap��������Ҫ�������ʣ�
        Map<String, List<Measure>> finalMap = new HashMap<>(metricMap.size());
        
        metricMap.forEach((key, measures) -> {
            // ȥ�ء�����Ⱥ���
            List<Measure> processedMeasures = postProcessMeasures(measures);
            finalMap.put(key, processedMeasures);
        });

        result.setMetricMap(finalMap);

        log.debug("����MetricMeasure��ɣ�periodId: {}, ����{}��metricKey, ��{}��Measure",
                periodId, finalMap.size(), size());

        return result;
    }

    /**
     * ��Measure�б���к���
     */
    private List<Measure> postProcessMeasures(List<Measure> measures) {
        if (measures == null || measures.isEmpty()) {
            return new ArrayList<>();
        }

        // 1. ȥ�أ�����measureCode��
        Map<String, Measure> uniqueMap = new LinkedHashMap<>();
        for (Measure measure : measures) {
            String key = measure.getMeasureCode();
            // ������ظ����������һ��
            uniqueMap.put(key, measure);
        }

        // 2. ���򣨰�measureCode��
        List<Measure> result = new ArrayList<>(uniqueMap.values());
        result.sort(Comparator.comparing(Measure::getMeasureCode));

        return result;
    }

    /**
     * ������ݣ���������Builder��
     */
    public void clear() {
        metricMap.clear();
        built = false;
    }
}