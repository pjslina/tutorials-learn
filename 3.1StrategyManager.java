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
 * 策略管理器 - 管理所有过滤和转换策略
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
        // 按优先级排序过滤策略
        sortedFilterStrategies = filterStrategies.stream()
                .sorted(Comparator.comparingInt(FilterStrategy::getPriority))
                .collect(Collectors.toList());

        log.info("初始化策略管理器，加载过滤策略: {}, 转换策略: {}",
                filterStrategies.size(), transformStrategies.size());

        filterStrategies.forEach(s -> log.info("  - 过滤策略: {} (优先级: {})", 
                s.getName(), s.getPriority()));
        transformStrategies.forEach(s -> log.info("  - 转换策略: {}", s.getName()));
    }

    /**
     * 执行所有适用的过滤策略
     * @return true表示通过过滤，false表示被过滤掉
     */
    public boolean executeFilterStrategies(
            BidsResp resp, 
            FilterMetadata metadata, 
            ProcessContext context) {

        for (FilterStrategy strategy : sortedFilterStrategies) {
            if (strategy.isApplicable(context)) {
                boolean passed = strategy.filter(resp, metadata, context);
                if (!passed) {
                    log.debug("数据被策略 {} 过滤: {}", strategy.getName(), resp);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行转换策略
     */
    public Measure executeTransformStrategy(
            BidsResp resp, 
            FilterMetadata metadata, 
            ProcessContext context) {

        // 找到第一个适用的转换策略
        TransformStrategy applicableStrategy = transformStrategies.stream()
                .filter(s -> s.isApplicable(context))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("没有找到适用的转换策略"));

        // 执行转换
        Measure measure = applicableStrategy.transform(resp, metadata, context);

        // 执行后处理
        applicableStrategy.postProcess(measure, resp, context);

        return measure;
    }

    /**
     * 获取所有激活的过滤策略名称
     */
    public List<String> getActiveFilterStrategyNames(ProcessContext context) {
        return sortedFilterStrategies.stream()
                .filter(s -> s.isApplicable(context))
                .map(FilterStrategy::getName)
                .collect(Collectors.toList());
    }
}