-- ==================== ���ظ�ִ�е����ݿ��ʼ���ű� ====================
-- ���ݿ⣺OpenGauss
-- ˵�����˽ű������ظ�ִ�У����������Ѵ��ڵĶ���

-- ��ʼ����
START TRANSACTION;

-- ==================== 1. ɾ���Ѵ��ڵı���������ϵ����ɾ���� ====================

-- ɾ�����������������ɾ����
DROP TABLE IF EXISTS metric_measure_rel CASCADE;

-- ɾ��������
DROP TABLE IF EXISTS measure_metadata CASCADE;

-- ɾ��ָ���
DROP TABLE IF EXISTS metric_metadata CASCADE;

-- ==================== 2. ����ָ��Ԫ���ݱ� ====================

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

-- ��������
CREATE INDEX idx_metric_code ON metric_metadata(metric_code);
CREATE INDEX idx_metric_type ON metric_metadata(metric_type);
CREATE INDEX idx_metric_category ON metric_metadata(metric_category);
CREATE INDEX idx_metric_status ON metric_metadata(status);

-- �ֶ�ע��
COMMENT ON TABLE metric_metadata IS 'ָ��Ԫ���ݱ�';
COMMENT ON COLUMN metric_metadata.metric_id IS 'ָ��ID';
COMMENT ON COLUMN metric_metadata.metric_code IS 'ָ�����';
COMMENT ON COLUMN metric_metadata.metric_name IS 'ָ������';
COMMENT ON COLUMN metric_metadata.metric_name_en IS 'ָ��Ӣ������';
COMMENT ON COLUMN metric_metadata.metric_type IS 'ָ�����ͣ�BASIC-����ָ�꣬DERIVED-����ָ��';
COMMENT ON COLUMN metric_metadata.metric_category IS 'ָ����ࣺFINANCIAL-����OPERATIONAL-��Ӫ';
COMMENT ON COLUMN metric_metadata.data_type IS '�������ͣ�NUMBER-��ֵ��PERCENTAGE-�ٷֱȣ�CURRENCY-����';
COMMENT ON COLUMN metric_metadata.unit IS '��λ';
COMMENT ON COLUMN metric_metadata.description IS 'ָ������';
COMMENT ON COLUMN metric_metadata.formula IS '���㹫ʽ';
COMMENT ON COLUMN metric_metadata.sort_order IS '�������';
COMMENT ON COLUMN metric_metadata.status IS '״̬��0-ͣ�ã�1-����';

-- ==================== 3. ��������Ԫ���ݱ� ====================

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

-- ��������
CREATE INDEX idx_measure_code ON measure_metadata(measure_code);
CREATE INDEX idx_measure_type ON measure_metadata(measure_type);
CREATE INDEX idx_measure_status ON measure_metadata(status);
CREATE INDEX idx_measure_key ON measure_metadata(is_key_measure);

-- �ֶ�ע��
COMMENT ON TABLE measure_metadata IS '����Ԫ���ݱ�';
COMMENT ON COLUMN measure_metadata.measure_id IS '����ID';
COMMENT ON COLUMN measure_metadata.measure_code IS '��������';
COMMENT ON COLUMN measure_metadata.measure_name IS '��������';
COMMENT ON COLUMN measure_metadata.measure_name_en IS '����Ӣ������';
COMMENT ON COLUMN measure_metadata.measure_type IS '�������ͣ�VALUE-ʵ��ֵ��TARGET-Ŀ��ֵ��VARIANCE-����ֵ';
COMMENT ON COLUMN measure_metadata.data_format IS '���ݸ�ʽ��0,0.00��0.00%��';
COMMENT ON COLUMN measure_metadata.decimal_places IS 'С��λ��';
COMMENT ON COLUMN measure_metadata.currency IS '�������ͣ�CNY��USD��EUR';
COMMENT ON COLUMN measure_metadata.unit IS '��λ';
COMMENT ON COLUMN measure_metadata.is_key_measure IS '�Ƿ�ؼ�������0-��1-��';
COMMENT ON COLUMN measure_metadata.sort_order IS '�������';
COMMENT ON COLUMN measure_metadata.status IS '״̬��0-ͣ�ã�1-����';

-- ==================== 4. ����ָ����������� ====================

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

-- ��������
CREATE INDEX idx_rel_metric_id ON metric_measure_rel(metric_id);
CREATE INDEX idx_rel_measure_id ON metric_measure_rel(measure_id);
CREATE INDEX idx_rel_status ON metric_measure_rel(status);
CREATE INDEX idx_rel_display_order ON metric_measure_rel(display_order);

-- �ֶ�ע��
COMMENT ON TABLE metric_measure_rel IS 'ָ�����������';
COMMENT ON COLUMN metric_measure_rel.rel_id IS '����ID';
COMMENT ON COLUMN metric_measure_rel.metric_id IS 'ָ��ID';
COMMENT ON COLUMN metric_measure_rel.measure_id IS '����ID';
COMMENT ON COLUMN metric_measure_rel.relation_type IS '�������ͣ�PRIMARY-��Ҫ��SECONDARY-��Ҫ';
COMMENT ON COLUMN metric_measure_rel.is_required IS '�Ƿ���裺0-��1-��';
COMMENT ON COLUMN metric_measure_rel.display_order IS '��ʾ˳��';
COMMENT ON COLUMN metric_measure_rel.calculation_rule IS '�������';
COMMENT ON COLUMN metric_measure_rel.status IS '״̬��0-ͣ�ã�1-����';

-- �ύ����
COMMIT;

-- ==================== 5. ��ʼ�����ݽű������ظ�ִ�У� ====================

START TRANSACTION;

-- ����������ݣ���������ϵ������գ�
DELETE FROM metric_measure_rel;
DELETE FROM measure_metadata;
DELETE FROM metric_metadata;

-- ����ָ������
INSERT INTO metric_metadata (metric_id, metric_code, metric_name, metric_name_en, metric_type, metric_category, data_type, unit, description, sort_order, status, create_by) VALUES
(1001, 'REVENUE', 'Ӫҵ����', 'Revenue', 'BASIC', 'FINANCIAL', 'CURRENCY', 'Ԫ', '��ҵ��һ��ʱ����������Ʒ���ṩ��������õ�����', 1, '1', 'system'),
(1002, 'PROFIT', '������', 'Net Profit', 'DERIVED', 'FINANCIAL', 'CURRENCY', 'Ԫ', '��ҵ���������ܶ��ȥ����˰��Ľ��', 2, '1', 'system'),
(1003, 'GROWTH_RATE', '������', 'Growth Rate', 'DERIVED', 'FINANCIAL', 'PERCENTAGE', '%', '��ӳ��ҵ��չ�ٶȵĶ�ָ̬��', 3, '1', 'system'),
(1004, 'EMPLOYEE_COUNT', 'Ա������', 'Employee Count', 'BASIC', 'OPERATIONAL', 'NUMBER', '��', '��ҵ��ǰ��ְԱ������', 4, '1', 'system'),
(1005, 'CUSTOMER_SATISFACTION', '�ͻ������', 'Customer Satisfaction', 'BASIC', 'OPERATIONAL', 'PERCENTAGE', '%', '�ͻ��Բ�Ʒ����������̶�', 5, '1', 'system'),
(1006, 'COST', 'Ӫҵ�ɱ�', 'Operating Cost', 'BASIC', 'FINANCIAL', 'CURRENCY', 'Ԫ', '��ҵ��������Ӫ�����з����ĸ���ɱ�֧��', 6, '1', 'system'),
(1007, 'ROI', 'Ͷ�ʻر���', 'Return on Investment', 'DERIVED', 'FINANCIAL', 'PERCENTAGE', '%', 'Ͷ�ʻر��ʣ�����Ͷ��Ч���ָ��', 7, '1', 'system'),
(1008, 'MARKET_SHARE', '�г��ݶ�', 'Market Share', 'BASIC', 'OPERATIONAL', 'PERCENTAGE', '%', '��ҵ��Ʒ��������г�����ռ�ı���', 8, '1', 'system');

-- �����������
INSERT INTO measure_metadata (measure_id, measure_code, measure_name, measure_name_en, measure_type, data_format, decimal_places, currency, unit, is_key_measure, sort_order, status, create_by) VALUES
(2001, 'ACTUAL_VALUE', 'ʵ��ֵ', 'Actual Value', 'VALUE', '0,0.00', 2, 'CNY', 'Ԫ', '1', 1, '1', 'system'),
(2002, 'TARGET_VALUE', 'Ŀ��ֵ', 'Target Value', 'TARGET', '0,0.00', 2, 'CNY', 'Ԫ', '0', 2, '1', 'system'),
(2003, 'LAST_YEAR_VALUE', 'ȥ��ͬ��', 'Last Year Value', 'VALUE', '0,0.00', 2, 'CNY', 'Ԫ', '0', 3, '1', 'system'),
(2004, 'VARIANCE', '����ֵ', 'Variance', 'VARIANCE', '0,0.00', 2, 'CNY', 'Ԫ', '0', 4, '1', 'system'),
(2005, 'VARIANCE_RATE', '������', 'Variance Rate', 'VARIANCE', '0.00%', 2, NULL, '%', '1', 5, '1', 'system'),
(2006, 'YOY_GROWTH', 'ͬ������', 'YoY Growth', 'VALUE', '0.00%', 2, NULL, '%', '1', 6, '1', 'system'),
(2007, 'COMPLETION_RATE', '�����', 'Completion Rate', 'VALUE', '0.00%', 2, NULL, '%', '1', 7, '1', 'system'),
(2008, 'BUDGET_VALUE', 'Ԥ��ֵ', 'Budget Value', 'TARGET', '0,0.00', 2, 'CNY', 'Ԫ', '0', 8, '1', 'system'),
(2009, 'FORECAST_VALUE', 'Ԥ��ֵ', 'Forecast Value', 'TARGET', '0,0.00', 2, 'CNY', 'Ԫ', '0', 9, '1', 'system'),
(2010, 'MOM_GROWTH', '��������', 'MoM Growth', 'VALUE', '0.00%', 2, NULL, '%', '0', 10, '1', 'system');

-- ���������ϵ
INSERT INTO metric_measure_rel (rel_id, metric_id, measure_id, relation_type, is_required, display_order, status, create_by) VALUES
-- Ӫҵ����Ķ��� (1001)
(3001, 1001, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3002, 1001, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3003, 1001, 2003, 'SECONDARY', '0', 3, '1', 'system'),
(3004, 1001, 2004, 'SECONDARY', '0', 4, '1', 'system'),
(3005, 1001, 2007, 'SECONDARY', '0', 5, '1', 'system'),
(3006, 1001, 2008, 'SECONDARY', '0', 6, '1', 'system'),
-- ������Ķ��� (1002)
(3007, 1002, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3008, 1002, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3009, 1002, 2006, 'SECONDARY', '0', 3, '1', 'system'),
(3010, 1002, 2007, 'SECONDARY', '0', 4, '1', 'system'),
-- �����ʵĶ��� (1003)
(3011, 1003, 2005, 'PRIMARY', '1', 1, '1', 'system'),
(3012, 1003, 2006, 'SECONDARY', '0', 2, '1', 'system'),
(3013, 1003, 2010, 'SECONDARY', '0', 3, '1', 'system'),
-- Ա�������Ķ��� (1004)
(3014, 1004, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3015, 1004, 2003, 'SECONDARY', '0', 2, '1', 'system'),
(3016, 1004, 2006, 'SECONDARY', '0', 3, '1', 'system'),
-- �ͻ�����ȵĶ��� (1005)
(3017, 1005, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3018, 1005, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3019, 1005, 2007, 'SECONDARY', '0', 3, '1', 'system'),
-- Ӫҵ�ɱ��Ķ��� (1006)
(3020, 1006, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3021, 1006, 2008, 'SECONDARY', '0', 2, '1', 'system'),
(3022, 1006, 2004, 'SECONDARY', '0', 3, '1', 'system'),
-- Ͷ�ʻر��ʵĶ��� (1007)
(3023, 1007, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3024, 1007, 2002, 'SECONDARY', '0', 2, '1', 'system'),
(3025, 1007, 2005, 'SECONDARY', '0', 3, '1', 'system'),
-- �г��ݶ�Ķ��� (1008)
(3026, 1008, 2001, 'PRIMARY', '1', 1, '1', 'system'),
(3027, 1008, 2003, 'SECONDARY', '0', 2, '1', 'system'),
(3028, 1008, 2006, 'SECONDARY', '0', 3, '1', 'system');

COMMIT;

-- ==================== 6. ������֤��ѯ ====================

-- ��ѯָ��ͳ��
SELECT 
    metric_type,
    COUNT(*) as metric_count
FROM metric_metadata
WHERE status = '1'
GROUP BY metric_type;

-- ��ѯ����ͳ��
SELECT 
    measure_type,
    COUNT(*) as measure_count
FROM measure_metadata
WHERE status = '1'
GROUP BY measure_type;

-- ��ѯ������ϵͳ��
SELECT 
    mm.metric_name,
    COUNT(mmr.measure_id) as measure_count
FROM metric_metadata mm
LEFT JOIN metric_measure_rel mmr ON mm.metric_id = mmr.metric_id AND mmr.status = '1'
WHERE mm.status = '1'
GROUP BY mm.metric_id, mm.metric_name
ORDER BY mm.sort_order;

-- ��ʾ�����Ϣ
SELECT '���ݿ��ʼ����ɣ�' as message;
SELECT 'ָ������: ' || COUNT(*) as info FROM metric_metadata WHERE status = '1';
SELECT '��������: ' || COUNT(*) as info FROM measure_metadata WHERE status = '1';
SELECT '������ϵ: ' || COUNT(*) as info FROM metric_measure_rel WHERE status = '1';