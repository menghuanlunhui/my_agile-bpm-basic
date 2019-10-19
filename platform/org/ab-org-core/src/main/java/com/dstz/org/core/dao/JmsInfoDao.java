package com.dstz.org.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.org.core.model.JmsInfo;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 加盟商信息
 */
@MapperScan
public interface JmsInfoDao extends BaseDao<String, JmsInfo> {

    JmsInfo getJmsInfoByAccountNumber(String accountNumber);

    int createJmsInfo(JmsInfo entity);

    int updateByAccountNumber(JmsInfo entity);

    JmsInfo getJmsInfoByCustAccountId(String custAccountId);
}
