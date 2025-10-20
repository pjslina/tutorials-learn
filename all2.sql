-- ==================== 数据清理脚本（谨慎使用） ====================

-- 清空所有数据但保留表结构
START TRANSACTION;

TRUNCATE TABLE metric_measure_rel CASCADE;
TRUNCATE TABLE measure_metadata CASCADE;
TRUNCATE TABLE metric_metadata CASCADE;

COMMIT;

-- ==================== 数据备份查询 ====================

-- 备份指标数据
SELECT * FROM metric_metadata WHERE status = '1' ORDER BY sort_order;

-- 备份度量数据
SELECT * FROM measure_metadata WHERE status = '1' ORDER BY sort_order;

-- 备份关联数据
SELECT * FROM metric_measure_rel WHERE status = '1' ORDER BY metric_id, display_order;

-- ==================== 常用维护查询 ====================

-- 查找没有度量的指标
SELECT mm.*
FROM metric_metadata mm
LEFT JOIN metric_measure_rel mmr ON mm.metric_id = mmr.metric_id AND mmr.status = '1'
WHERE mm.status = '1'
  AND mmr.metric_id IS NULL;

-- 查找没有被使用的度量
SELECT msm.*
FROM measure_metadata msm
LEFT JOIN metric_measure_rel mmr ON msm.measure_id = mmr.measure_id AND mmr.status = '1'
WHERE msm.status = '1'
  AND mmr.measure_id IS NULL;

-- 查询每个指标的度量数量
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

-- ==================== 性能优化相关 ====================

-- 分析表统计信息
ANALYZE metric_metadata;
ANALYZE measure_metadata;
ANALYZE metric_measure_rel;

-- 重建索引（如果需要）
REINDEX TABLE metric_metadata;
REINDEX TABLE measure_metadata;
REINDEX TABLE metric_measure_rel;

-- 查看表大小
SELECT 
    schemaname,
    tablename,
    pg_size_pretty(pg_total_relation_size(schemaname||'.'||tablename)) AS size
FROM pg_tables
WHERE tablename IN ('metric_metadata', 'measure_metadata', 'metric_measure_rel')
ORDER BY pg_total_relation_size(schemaname||'.'||tablename) DESC;