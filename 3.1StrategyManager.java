package org.example.strategy;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.BidsResp;
import org.example.dto.Measure;
import org.example.service.OptimizedBidsDataProcessor.FilterMetadata;
import org.example.service.OptimizedBidsDataProcessor.ProcessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ���Թ����� - �������й��˺�ת������
 */
@Slf4j
@Component
public class StrategyManager {

    @Autowired
    private List<FilterStrategy> filterStrategies;

    @Autowired
    private List<TransformStrategy> transformStrategies;

    private List<FilterStrategy> sortedFilterStrategies;

    @PostConstruct
    public void init() {
        // �����ȼ�������˲���
        sortedFilterStrategies = filterStrategies.stream()
                .sorted(Comparator.comparingInt(FilterStrategy::getPriority))
                .collect(Collectors.toList());

        log.info("��ʼ�����Թ����������ع��˲���: {}, ת������: {}",
                filterStrategies.size(), transformStrategies.size());

        filterStrategies.forEach(s -> log.info("  - ���˲���: {} (���ȼ�: {})", 
                s.getName(), s.getPriority()));
        transformStrategies.forEach(s -> log.info("  - ת������: {}", s.getName()));
    }

    /**
     * ִ���������õĹ��˲���
     * @return true��ʾͨ�����ˣ�false��ʾ�����˵�
     */
    public boolean executeFilterStrategies(
            BidsResp resp, 
            FilterMetadata metadata, 
            ProcessContext context) {

        for (FilterStrategy strategy : sortedFilterStrategies) {
            if (strategy.isApplicable(context)) {
                boolean passed = strategy.filter(resp, metadata, context);
                if (!passed) {
                    log.debug("���ݱ����� {} ����: {}", strategy.getName(), resp);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * ִ��ת������
     */
    public Measure executeTransformStrategy(
            BidsResp resp, 
            FilterMetadata metadata, 
            ProcessContext context) {

        // �ҵ���һ�����õ�ת������
        TransformStrategy applicableStrategy = transformStrategies.stream()
                .filter(s -> s.isApplicable(context))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("û���ҵ����õ�ת������"));

        // ִ��ת��
        Measure measure = applicableStrategy.transform(resp, metadata, context);

        // ִ�к���
        applicableStrategy.postProcess(measure, resp, context);

        return measure;
    }

    /**
     * ��ȡ���м���Ĺ��˲�������
     */
    public List<String> getActiveFilterStrategyNames(ProcessContext context) {
        return sortedFilterStrategies.stream()
                .filter(s -> s.isApplicable(context))
                .map(FilterStrategy::getName)
                .collect(Collectors.toList());
    }
}