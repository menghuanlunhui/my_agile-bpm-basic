package com.dstz.org.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.org.core.model.SysDistr;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

/**
 * 加盟商（带中心、省区、片区、品牌）信息
 */
@MapperScan
public interface SysDistrDao extends BaseDao<String, SysDistr> {

    SysDistr getSysDistrByCodeAndId(@Param("brandCode") String brandCode, @Param("custAccountId") String custAccountId);

    int updateSysDistrByAccountNumber(SysDistr sysDistr);

    int createSysDistr(SysDistr sysDistr);
}
