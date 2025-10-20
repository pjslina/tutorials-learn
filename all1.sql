-- ==================== 可重复执行的数据库初始化脚本 ====================
-- 数据库：OpenGauss
-- 说明：此脚本可以重复执行，会先清理已存在的对象

-- 开始事务
START TRANSACTION;

-- ==================== 1. 删除已存在的表（按依赖关系逆序删除） ====================

-- 删除关联表（有外键，先删除）
DROP TABLE IF EXISTS metric_measure_rel CASCADE;

-- 删除度量表
DROP TABLE IF EXISTS measure_metadata CASCADE;

-- 删除指标表
DROP TABLE IF EXISTS metric_metadata CASCADE;

-- ==================== 2. 创建指标元数据表 ====================

CREATE TABLE metric_metadata (
    metric_id BIGINT NOT NULL,
    metric_code VARCHAR(100) NOT NULL,
    metric_name VARCHAR(200) NOT NULL,
    metric_name_en VARCHAR(200),
    metric_type VARCHAR(50),
    metric_category VARCHAR(100),
    data_type VARCHAR(50) DEFAULT 'NUMBER',
    unit VARCHAR(50),
    description VARCHAR(500),
    formula TEXT,
    sort_order INT DEFAULT 0,
    status CHAR(1) DEFAULT '1',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(64),
    update_by VARCHAR(64),
    remark VARCHAR(500),
    CONSTRAINT pk_metric_metadata PRIMARY KEY (metric_id),
    CONSTRAINT uk_metric_code UNIQUE (metric_code)
);

-- 创建索引
CREATE INDEX idx_metric_code ON metric_metadata(metric_code);
CREATE INDEX idx_metric_type ON metric_metadata(metric_type);
CREATE INDEX idx_metric_category ON metric_metadata(metric_category);
CREATE INDEX idx_metric_status ON metric_metadata(status);

-- 字段注释
COMMENT ON TABLE metric_metadata IS '指标元数据表';
COMMENT ON COLUMN metric_metadata.metric_id IS '指标ID';
COMMENT ON COLUMN metric_metadata.metric_code IS '指标编码';
COMMENT ON COLUMN metric_metadata.metric_name IS '指标名称';
COMMENT ON COLUMN metric_metadata.metric_name_en IS '指标英文名称';
COMMENT ON COLUMN metric_metadata.metric_type IS '指标类型：BASIC-基础指标，DERIVED-派生指标';
COMMENT ON COLUMN metric_metadata.metric_category IS '指标分类：FINANCIAL-财务，OPERATIONAL-运营';
COMMENT ON COLUMN metric_metadata.data_type IS '数据类型：NUMBER-数值，PERCENTAGE-百分比，CURRENCY-货币';
COMMENT ON COLUMN metric_metadata.unit IS '单位';
COMMENT ON COLUMN metric_metadata.description IS '指标描述';
COMMENT ON COLUMN metric_metadata.formula IS '计算公式';
COMMENT ON COLUMN metric_metadata.sort_order IS '排序序号';
COMMENT ON COLUMN metric_metadata.status IS '状态：0-停用，1-启用';

-- ==================== 3. 创建度量元数据表 ====================

CREATE TABLE measure_metadata (
    measure_id BIGINT NOT NULL,
    measure_code VARCHAR(100) NOT NULL,
    measure_name VARCHAR(200) NOT NULL,
    measure_name_en VARCHAR(200),
    measure_type VARCHAR(50),
    data_format VARCHAR(50),
    decimal_places INT DEFAULT 2,
    currency VARCHAR(20),
    unit VARCHAR(50),
    description VARCHAR(500),
    is_key_measure CHAR(1) DEFAULT '0',
    sort_order INT DEFAULT 0,
    status CHAR(1) DEFAULT '1',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(64),
    update_by VARCHAR(64),
    remark VARCHAR(500),
    CONSTRAINT pk_measure_metadata PRIMARY KEY (measure_id),
    CONSTRAINT uk_measure_code UNIQUE (measure_code)
);

-- 创建索引
CREATE INDEX idx_measure_code ON measure_metadata(measure_code);
CREATE INDEX idx_measure_type ON measure_metadata(measure_type);
CREATE INDEX idx_measure_status ON measure_metadata(status);
CREATE INDEX idx_measure_key ON measure_metadata(is_key_measure);

-- 字段注释
COMMENT ON TABLE measure_metadata IS '度量元数据表';
COMMENT ON COLUMN measure_metadata.measure_id IS '度量ID';
COMMENT ON COLUMN measure_metadata.measure_code IS '度量编码';
COMMENT ON COLUMN measure_metadata.measure_name IS '度量名称';
COMMENT ON COLUMN measure_metadata.measure_name_en IS '度量英文名称';
COMMENT ON COLUMN measure_metadata.measure_type IS '度量类型：VALUE-实际值，TARGET-目标值，VARIANCE-差异值';
COMMENT ON COLUMN measure_metadata.data_format IS '数据格式：0,0.00、0.00%等';
COMMENT ON COLUMN measure_metadata.decimal_places IS '小数位数';
COMMENT ON COLUMN measure_metadata.currency IS '货币类型：CNY、USD、EUR';
COMMENT ON COLUMN measure_metadata.unit IS '单位';
COMMENT ON COLUMN measure_metadata.is_key_measure IS '是否关键度量：0-否，1-是';
COMMENT ON COLUMN measure_metadata.sort_order IS '排序序号';
COMMENT ON COLUMN measure_metadata.status IS '状态：0-停用，1-启用';

-- ==================== 4. 创建指标度量关联表 ====================

CREATE TABLE metric_measure_rel (
    rel_id BIGINT NOT NULL,
    metric_id BIGINT NOT NULL,
    measure_id BIGINT NOT NULL,
    relation_type VARCHAR(50),
    is_required CHAR(1) DEFAULT '0',
    display_order INT DEFAULT 0,
    calculation_rule TEXT,
    status CHAR(1) DEFAULT '1',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    create_by VARCHAR(64),
    update_by VARCHAR(64),
    remark VARCHAR(500),
    CONSTRAINT pk_metric_measure_rel PRIMARY KEY (rel_id),
    CONSTRAINT uk_metric_measure UNIQUE (metric_id, measure_id),
    CONSTRAINT fk_rel_metric FOREIGN KEY (metric_id) REFERENCES metric_metadata(metric_id) ON DELETE CASCADE,
    CONSTRAINT fk_rel_measure FOREIGN KEY (measure_id) REFERENCES measure_metadata(measure_id) ON DELETE CASCADE
);

-- 创建索引
CREATE INDEX idx_rel_metric_id ON metric_measure_rel(metric_id);
CREATE INDEX idx_rel_measure_id ON metric_measure_rel(measure_id);
CREATE INDEX idx_rel_status ON metric_measure_rel(status);
CREATE INDEX idx_rel_display_order ON metric_measure_rel(display_order);

-- 字段注释
COMMENT ON TABLE metric_measure_rel IS '指标度量关联表';
COMMENT ON COLUMN metric_measure_rel.rel_id IS '关联ID';
COMMENT ON COLUMN metric_measure_rel.metric_id IS '指标ID';
COMMENT ON COLUMN metric_measure_rel.measure_id IS '度量ID';
COMMENT ON COLUMN metric_measure_rel.relation_type IS '关联类型：PRIMARY-主要，SECONDARY-次要';
COMMENT ON COLUMN metric_measure_rel.is_required IS '是否必需：0-否，1-是';
COMMENT ON COLUMN metric_measure_rel.display_order IS '显示顺序';
COMMENT ON COLUMN metric_measure_rel.calculation_rule IS '计算规则';
COMMENT ON COLUMN metric_measure_rel.status IS '状态：0-停用，1-启用';

-- 提交事务
COMMIT;

-- ==================== 5. 初始化数据脚本（可重复执行） ====================

START TRANSACTION;

-- 清空已有数据（按依赖关系逆序清空）
DELETE FROM metric_measure_rel;
DELETE FROM measure_metadata;
DELETE FROM metric_metadata;

-- 插入指标数据
INSERT INTO metric_metadata (metric_id, metric_code, metric_name, metric_name_en, metric_type, metric_category, data_type, unit, description, sort_order, status, create_by) VALUES
(1001, 'REVENUE', '营业收入', 'Revenue', 'BASIC', 'FINANCIAL', 'CURRENCY', '元', '企业在一定时期内销售商品或提供劳务所获得的收入', 1, '1', 'system'),
(1002, 'PROFIT', '净利润', 'Net Profit', 'DERIVED', 'FINANCIAL', 'CURRENCY', '元', '企业当期利润总额减去所得税后的金额', 2, '1', 'system'),
(1003, 'GROWTH_RATE', '增长率', 'Growth Rate', 'DERIVED', 'FINANCIAL', 'PERCENTAGE', '%', '反映企业发展速度的动态指标', 3, '1', 'system'),
(1004, 'EMPLOYEE_COUNT', '员工数量', 'Employee Count', 'BASIC', 'OPERATIONAL', 'NUMBER', '人', '企业当前在职员工总数', 4, '1', 'system'),
(1005, 'CUSTOMER_SATISFACTION', '客户满意度', 'Customer Satisfaction', 'BASIC', 'OPERATIONAL', 'PERCENTAGE', '%', '客户对产品或服务的满意程度', 5, '1', 'system'),
(1006, 'COST', '营业成本', 'Operating Cost', 'BASIC', 'FINANCIAL', 'CURRENCY', '元', '企业在生产经营过程中发生的各项成本支出', 6, '1', 'system'),
(1007, 'ROI', '投资回报率', 'Return on Investment', 'DERIVED', 'FINANCIAL', 'PERCENTAGE', '%', '投资回报率，衡量投资效益的指标', 7, '1', 'system'),
(1008, 'MARKET_SHARE', '市场份额', 'Market Share', 'BASIC', 'OPERATIONAL', 'PERCENTAGE', '%', '企业产品或服务在市场中所占的比例', 8, '1', 'system');

-- 插入度量数据
INSERT INTO measure_metadata (measure_id, measure_code, measure_name, measure_name_en, measure_type, data_format, decimal_places, currency, unit, is_key_measure, sort_order, status, create_by) VALUES
(2001, 'ACTUAL_VALUE', '实际值', 'Actual Value', 'VALUE', '0,0.00', 2, 'CNY', '元', '1', 1, '1', 'system'),
(2002, 'TARGET_VALUE', '目标值', 'Target Value', 'TARGET', '0,0.00', 2, 'CNY', '元', '0', 2, '1', 'system'),
(2003, 'LAST_YEAR_VALUE', '去年同期', 'Last Year Value', 'VALUE', '0,0.00', 2, 'CNY', '元', '0', 3, '1', 'system'),
(2004, 'VARIANCE', '差异值', 'Variance', 'VARIANCE', '0,0.00', 2, 'CNY', '元', '0', 4, '1', 'system'),
(2005, 'VARIANCE_RATE', '差异率', 'Variance Rate', 'VARIANCE', '0.00%', 2, NULL, '%', '1', 5, '1', 'system'),
(2006, 'YOY_GROWTH', '同比增长', 'YoY Growth', 'VALUE', '0.00%', 2, NULL, '%', '1', 6, '1', 'system'),
(2007, 'COMPLETION_RATE', '完成率', 'Completion Rate', 'VALUE', '0.00%', 2, NULL, '%', '1', 7, '1', 'system'),
(2008, 'BUDGET_VALUE', '预算值', 'Budget Value', 'TARGET', '0,0.00', 2, 'CNY', '元', '0', 8, '1', 'system'),
(2009, 'FORECAST_VALUE', '预测值', 'Forecast Value', 'TARGET', '0,0.00', 2, 'CNY', '元', '0', 9, '1', 'system'),
(2010, 'MOM_GROWTH', '环比增长', 'MoM Growth', 'VALUE', '0.00%', 2, NULL, '%', '0', 10, '1', 'system');

-- 插入关联关系
INSERT INTO metric_measure_rel (rel_id, metric_id, measure_id, relation_type, is_required, display_order, status, create_by) VALUES
-- 营业收入的度量 (1001)
(3001, 1001, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3002, 1001, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3003, 1001, 2003, 'SECONDARY', '0', 3, '1', 'system'),
(3004, 1001, 2004, 'SECONDARY', '0', 4, '1', 'system'),
(3005, 1001, 2007, 'SECONDARY', '0', 5, '1', 'system'),
(3006, 1001, 2008, 'SECONDARY', '0', 6, '1', 'system'),
-- 净利润的度量 (1002)
(3007, 1002, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3008, 1002, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3009, 1002, 2006, 'SECONDARY', '0', 3, '1', 'system'),
(3010, 1002, 2007, 'SECONDARY', '0', 4, '1', 'system'),
-- 增长率的度量 (1003)
(3011, 1003, 2005, 'PRIMARY', '1', 1, '1', 'system'),
(3012, 1003, 2006, 'SECONDARY', '0', 2, '1', 'system'),
(3013, 1003, 2010, 'SECONDARY', '0', 3, '1', 'system'),
-- 员工数量的度量 (1004)
(3014, 1004, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3015, 1004, 2003, 'SECONDARY', '0', 2, '1', 'system'),
(3016, 1004, 2006, 'SECONDARY', '0', 3, '1', 'system'),
-- 客户满意度的度量 (1005)
(3017, 1005, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3018, 1005, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3019, 1005, 2007, 'SECONDARY', '0', 3, '1', 'system'),
-- 营业成本的度量 (1006)
(3020, 1006, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3021, 1006, 2008, 'SECONDARY', '0', 2, '1', 'system'),
(3022, 1006, 2004, 'SECONDARY', '0', 3, '1', 'system'),
-- 投资回报率的度量 (1007)
(3023, 1007, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3024, 1007, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3025, 1007, 2005, 'SECONDARY', '0', 3, '1', 'system'),
-- 市场份额的度量 (1008)
(3026, 1008, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3027, 1008, 2003, 'SECONDARY', '0', 2, '1', 'system'),
(3028, 1008, 2006, 'SECONDARY', '0', 3, '1', 'system');

COMMIT;

-- ==================== 6. 数据验证查询 ====================

-- 查询指标统计
SELECT 
    metric_type,
    COUNT(*) as metric_count
FROM metric_metadata
WHERE status = '1'
GROUP BY metric_type;

-- 查询度量统计
SELECT 
    measure_type,
    COUNT(*) as measure_count
FROM measure_metadata
WHERE status = '1'
GROUP BY measure_type;

-- 查询关联关系统计
SELECT 
    mm.metric_name,
    COUNT(mmr.measure_id) as measure_count
FROM metric_metadata mm
LEFT JOIN metric_measure_rel mmr ON mm.metric_id = mmr.metric_id AND mmr.status = '1'
WHERE mm.status = '1'
GROUP BY mm.metric_id, mm.metric_name
ORDER BY mm.sort_order;

-- 显示完成信息
SELECT '数据库初始化完成！' as message;
SELECT '指标数量: ' || COUNT(*) as info FROM metric_metadata WHERE status = '1';
SELECT '度量数量: ' || COUNT(*) as info FROM measure_metadata WHERE status = '1';
SELECT '关联关系: ' || COUNT(*) as info FROM metric_measure_rel WHERE status = '1';