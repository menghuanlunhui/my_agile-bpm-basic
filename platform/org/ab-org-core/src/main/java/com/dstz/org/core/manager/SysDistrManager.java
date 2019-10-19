package com.dstz.org.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.org.core.model.SysDistr;

public interface SysDistrManager  extends Manager<String, SysDistr> {

    SysDistr getSysDistrByCodeAndId(String brandCode, String custAccountId);

    int updateSysDistrByAccountNumber(SysDistr sysDistr);

    int createSysDistr(SysDistr sysDistr);
}
