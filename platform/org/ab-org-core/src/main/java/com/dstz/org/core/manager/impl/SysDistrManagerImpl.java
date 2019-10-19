package com.dstz.org.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.org.core.dao.SysDistrDao;
import com.dstz.org.core.manager.SysDistrManager;
import com.dstz.org.core.model.SysDistr;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenyun
 * @projectName agile-bpm
 * @description: TODO
 * @date 2019/10/17 17:26
 */
@Service("sysDistrManager")
public class SysDistrManagerImpl extends BaseManager<String, SysDistr> implements SysDistrManager {

    @Resource
    SysDistrDao sysDistrDao;


    @Override
    public SysDistr getSysDistrByCodeAndId(String brandCode, String custAccountId) {
        return sysDistrDao.getSysDistrByCodeAndId(brandCode,custAccountId);
    }

    @Override
    public int updateSysDistrByAccountNumber(SysDistr sysDistr) {
        return sysDistrDao.updateSysDistrByAccountNumber(sysDistr);
    }

    @Override
    public int createSysDistr(SysDistr sysDistr) {
        return sysDistrDao.createSysDistr(sysDistr);
    }
}
