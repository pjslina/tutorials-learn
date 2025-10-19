// ==================== 内部类定义 ====================

/**
 * 处理上下文 - 封装所有输入参数
 */
@Data
public static class ProcessContext {
    private int pageSize = 100;
    private Map<String, Object> inputParams = new HashMap<>();
    private Set<String> requiredMetricCodes;
    private Set<String> excludeOrgCodes;
    private String startPeriod;
    private String endPeriod;

    public ProcessContext() {
    }

    public ProcessContext(int pageSize) {
        this.pageSize = pageSize;
    }

    // 流式构建方法
    public ProcessContext withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public ProcessContext withInputParam(String key, Object value) {
        this.inputParams.put(key, value);
        return this;
    }

    public ProcessContext withRequiredMetricCodes(Set<String> codes) {
        this.requiredMetricCodes = codes;
        return this;
    }

    public ProcessContext withExcludeOrgCodes(Set<String> codes) {
        this.excludeOrgCodes = codes;
        return this;
    }

    public ProcessContext withPeriodRange(String start, String end) {
        this.startPeriod = start;
        this.endPeriod = end;
        return this;
    }
}

/**
 * 过滤元数据 - 封装所有过滤需要的元数据
 */
@Data
public static class FilterMetadata {
    private Map<String, String> metricMapping = new HashMap<>();
    private Set<String> validDomainCodes = new HashSet<>();
    private Map<String, List<String>> orgHierarchy = new HashMap<>();
    private Map<String, Double> exchangeRates = new HashMap<>();
    private Map<String, String> unitConversions = new HashMap<>();

    public FilterMetadata() {
    }

    // 流式构建方法
    public FilterMetadata withMetricMapping(Map<String, String> mapping) {
        this.metricMapping = mapping;
        return this;
    }

    public FilterMetadata withValidDomainCodes(Set<String> codes) {
        this.validDomainCodes = codes;
        return this;
    }

    public FilterMetadata withOrgHierarchy(Map<String, List<String>> hierarchy) {
        this.orgHierarchy = hierarchy;
        return this;
    }

    public FilterMetadata withExchangeRates(Map<String, Double> rates) {
        this.exchangeRates = rates;
        return this;
    }
}

/**
 * 分页结果
 */
@Data
public static class PageResult<T> {
    private long total;
    private List<T> data;
    private int pageNum;
    private int pageSize;

    public PageResult() {
    }

    public PageResult(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }
}

/**
 * 处理结果
 */
@Data
public static class ProcessResult {
    private boolean success;
    private long totalRecords;
    private long processedRecords;
    private int totalPages;
    private long processDuration;
    private List<MetricMeasure> data;
    private List<String> activeStrategies;
    private List<String> errors = new ArrayList<>();

    public void incrementProcessedRecords(int count) {
        this.processedRecords += count;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public long getFilteredRecords() {
        return totalRecords - processedRecords;
    }
}