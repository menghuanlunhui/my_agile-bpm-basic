package com.dstz.org.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.org.core.model.JmsInfo;

/**
 * 加盟商信息
 */
public interface JmsInfoManager extends Manager<String, JmsInfo> {

    JmsInfo getJmsInfoByAccountNumber(String accountNumber);

    JmsInfo getJmsInfoByCustAccountId(String custAccountId);

    int updateByAccountNumber(JmsInfo jmsInfo);

    int createJmsInfo(JmsInfo jmsInfo);
}
