package org.example.service;

import org.example.dto.BidsResp;
import org.springframework.stereotype.Service;

/**
 * ���˷��� - ��װ���ӵĹ����߼�
 */
@Service
public class BidsFilterService {

    /**
     * �ж�BidsResp�Ƿ�Ӧ�ñ�����
     * ���Ｏ�д������и��ӵĹ��˹���
     */
    public boolean shouldInclude(
            BidsResp resp,
            OptimizedBidsDataProcessor.FilterMetadata metadata,
            OptimizedBidsDataProcessor.ProcessContext context) {

        // 1. ������εĹ���
        if (context.getRequiredMetricCodes() != null && 
            !context.getRequiredMetricCodes().isEmpty()) {
            if (!context.getRequiredMetricCodes().contains(resp.getMetricCode())) {
                return false;
            }
        }

        // 2. ����Ԫ���ݵĹ���
        if (metadata.getValidDomainCodes() != null) {
            if (!metadata.getValidDomainCodes().contains(resp.getDomainCode())) {
                return false;
            }
        }

        // 3. �ų��ض���֯
        if (context.getExcludeOrgCodes() != null && 
            context.getExcludeOrgCodes().contains(resp.getOrgCode())) {
            return false;
        }

        // 4. ���ӵ�ҵ�����
        if (!validateBusinessRules(resp, metadata, context)) {
            return false;
        }

        // 5. �����������
        if (!isDataValid(resp)) {
            return false;
        }

        return true;
    }

    /**
     * ҵ�������֤
     */
    private boolean validateBusinessRules(
            BidsResp resp,
            OptimizedBidsDataProcessor.FilterMetadata metadata,
            OptimizedBidsDataProcessor.ProcessContext context) {
        
        // TODO: ʵ�־����ҵ�����
        // ���磺��֯�㼶��顢ָ����Ч�Լ���
        
        return true;
    }

    /**
     * ������Ч�Լ��
     */
    private boolean isDataValid(BidsResp resp) {
        // �������ֶ�
        if (resp.getMetricCode() == null || resp.getMetricCode().isEmpty()) {
            return false;
        }
        
        if (resp.getMeasureValue() == null || resp.getMeasureValue().isEmpty()) {
            return false;
        }

        // �����ֵ��Ч��
        try {
            Double.parseDouble(resp.getMeasureValue());
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}