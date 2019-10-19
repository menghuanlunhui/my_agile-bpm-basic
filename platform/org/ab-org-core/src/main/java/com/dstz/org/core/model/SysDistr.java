package com.dstz.org.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 加盟商（带中心、省区、片区、品牌）信息
 */
public class SysDistr implements Serializable{
	
	private static final long serialVersionUID = 8073421778677293722L;
	private Integer distrid;
	private String distrcode;
	private String distrname;
	private String administrativeArea;
	private String province;
	private String city;
	private String accountShortName;
	private String registrationAddress;
	private String legalPerson;
	private String custAccountId;
	private String brandCode;
	private String source;
	private Integer distrstatus;
	private String brandName;
	private String provinceName;
	private String cityName;
	private String centerCode;
	private String centerName;
	private String sqCode;
	private String sqName;
	private String pqCode;
	private String pqName;
	private Date createTime;
	private Date updateTime;
	private String curYearRebateMoney;
	private String totalRebateMoney;

	public Integer getDistrid() {
		return distrid;
	}

	public void setDistrid(Integer distrid) {
		this.distrid = distrid;
	}

	public String getDistrcode() {
		return distrcode;
	}

	public void setDistrcode(String distrcode) {
		this.distrcode = distrcode;
	}

	public String getDistrname() {
		return distrname;
	}

	public void setDistrname(String distrname) {
		this.distrname = distrname;
	}

	public String getAdministrativeArea() {
		return administrativeArea;
	}

	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccountShortName() {
		return accountShortName;
	}

	public void setAccountShortName(String accountShortName) {
		this.accountShortName = accountShortName;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public void setRegistrationAddress(String registrationAddress) {
		this.registrationAddress = registrationAddress;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getCustAccountId() {
		return custAccountId;
	}

	public void setCustAccountId(String custAccountId) {
		this.custAccountId = custAccountId;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getDistrstatus() {
		return distrstatus;
	}

	public void setDistrstatus(Integer distrstatus) {
		this.distrstatus = distrstatus;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getSqCode() {
		return sqCode;
	}

	public void setSqCode(String sqCode) {
		this.sqCode = sqCode;
	}

	public String getSqName() {
		return sqName;
	}

	public void setSqName(String sqName) {
		this.sqName = sqName;
	}

	public String getPqCode() {
		return pqCode;
	}

	public void setPqCode(String pqCode) {
		this.pqCode = pqCode;
	}

	public String getPqName() {
		return pqName;
	}

	public void setPqName(String pqName) {
		this.pqName = pqName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCurYearRebateMoney() {
		return curYearRebateMoney;
	}

	public void setCurYearRebateMoney(String curYearRebateMoney) {
		this.curYearRebateMoney = curYearRebateMoney;
	}

	public String getTotalRebateMoney() {
		return totalRebateMoney;
	}

	public void setTotalRebateMoney(String totalRebateMoney) {
		this.totalRebateMoney = totalRebateMoney;
	}
}
