-- ==================== ��������ű�������ʹ�ã� ====================

-- ����������ݵ�������ṹ
START TRANSACTION;

TRUNCATE TABLE metric_measure_rel CASCADE;
TRUNCATE TABLE measure_metadata CASCADE;
TRUNCATE TABLE metric_metadata CASCADE;

COMMIT;

-- ==================== ���ݱ��ݲ�ѯ ====================

-- ����ָ������
SELECT * FROM metric_metadata WHERE status = '1' ORDER BY sort_order;

-- ���ݶ�������
SELECT * FROM measure_metadata WHERE status = '1' ORDER BY sort_order;

-- ���ݹ�������
SELECT * FROM metric_measure_rel WHERE status = '1' ORDER BY metric_id, display_order;

-- ==================== ����ά����ѯ ====================

-- ����û�ж�����ָ��
SELECT mm.*
FROM metric_metadata mm
LEFT JOIN metric_measure_rel mmr ON mm.metric_id = mmr.metric_id AND mmr.status = '1'
WHERE mm.status = '1'
  AND mmr.metric_id IS NULL;

-- ����û�б�ʹ�õĶ���
SELECT msm.*
FROM measure_metadata msm
LEFT JOIN metric_measure_rel mmr ON msm.measure_id = mmr.measure_id AND mmr.status = '1'
WHERE msm.status = '1'
  AND mmr.measure_id IS NULL;

-- ��ѯÿ��ָ��Ķ�������
SELECT 
    mm.metric_code,
    mm.metric_name,
    COUNT(mmr.measure_id) as measure_count,
    STRING_AGG(msm.measure_name, ', ' ORDER BY mmr.display_order) as measure_list
FROM metric_metadata mm
LEFT JOIN metric_measure_rel mmr ON mm.metric_id = mmr.metric_id AND mmr.status = '1'
LEFT JOIN measure_metadata msm ON mmr.measure_id = msm.measure_id AND msm.status = '1'
WHERE mm.status = '1'
GROUP BY mm.metric_id, mm.metric_code, mm.metric_name
ORDER BY mm.sort_order;

-- ==================== �����Ż���� ====================

-- ������ͳ����Ϣ
ANALYZE metric_metadata;
ANALYZE measure_metadata;
ANALYZE metric_measure_rel;

-- �ؽ������������Ҫ��
REINDEX TABLE metric_metadata;
REINDEX TABLE measure_metadata;
REINDEX TABLE metric_measure_rel;

-- �鿴���С
SELECT 
    schemaname,
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS size
FROM pg_tables
WHERE tablename IN ('metric_metadata', 'measure_metadata', 'metric_measure_rel')
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;