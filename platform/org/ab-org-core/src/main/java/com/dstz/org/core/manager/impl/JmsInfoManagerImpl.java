package com.dstz.org.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.org.core.dao.JmsInfoDao;
import com.dstz.org.core.manager.JmsInfoManager;
import com.dstz.org.core.model.JmsInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenyun
 * @projectName agile-bpm
 * @description: TODO
 * @date 2019/10/17 15:53
 */
@Service("jmsInfoManager")
public class JmsInfoManagerImpl extends BaseManager<String, JmsInfo> implements JmsInfoManager {

    @Resource
    JmsInfoDao jmsInfoDao;

    @Override
    public JmsInfo getJmsInfoByAccountNumber(String accountNumber) {
        return jmsInfoDao.getJmsInfoByAccountNumber(accountNumber);
    }

    @Override
    public JmsInfo getJmsInfoByCustAccountId(String custAccountId) {
        return jmsInfoDao.getJmsInfoByCustAccountId(custAccountId);
    }

    @Override
    public void create(JmsInfo entity) {
        jmsInfoDao.create(entity);
    }

    @Override
    public int updateByAccountNumber(JmsInfo entity) {
        return jmsInfoDao.updateByAccountNumber(entity);
    }

    @Override
    public int createJmsInfo(JmsInfo jmsInfo) {
        return jmsInfoDao.createJmsInfo(jmsInfo);
    }

}
