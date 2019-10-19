package com.dstz.org.core.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenyun
 * @projectName agile-bpm
 * @description: TODO
 * @date 2019/10/17 15:38
 */
public class JmsInfo implements Serializable {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 关联品牌id
     */
    private String custAccountId;
    /**
     * 加盟商编码
     */
    private String accountNumber;
    /**
     * 加盟商名称
     */
    private String accountName;
    /**
     * 加盟商简称
     */
    private String accountShortName;
    /**
     * 税务登记号
     */
    private String taxRegistrationNum;
    /**
     * 注册地址
     */
    private String registrationAddress;
    /**
     * 法人名称
     */
    private String legalPerson;
    /**
     * 法人身份证账号
     */
    private String legalIdentification;
    /**
     * 电话
     */
    private String contactPhone;
    /**
     * 账号所有者
     */
    private String accountOwner;
    /**
     * 账号所有者身份证
     */
    private String accountIdentification;
    /**
     * 组织类型
     */
    private String organizationType;
    /**
     * 省份编码
     */
    private String province;

    /**
     * 省份编码
     */
    private String provinceName;
    /**
     * 城市编码
     */
    private String city;

    /**
     * 省份编码
     */
    private String cityName;
    /**
     * 账号等级
     */
    private String accountLevel;
    /**
     * 状态PUBLISHED
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustAccountId() {
        return custAccountId;
    }

    public void setCustAccountId(String custAccountId) {
        this.custAccountId = custAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountShortName() {
        return accountShortName;
    }

    public void setAccountShortName(String accountShortName) {
        this.accountShortName = accountShortName;
    }

    public String getTaxRegistrationNum() {
        return taxRegistrationNum;
    }

    public void setTaxRegistrationNum(String taxRegistrationNum) {
        this.taxRegistrationNum = taxRegistrationNum;
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

    public String getLegalIdentification() {
        return legalIdentification;
    }

    public void setLegalIdentification(String legalIdentification) {
        this.legalIdentification = legalIdentification;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountIdentification() {
        return accountIdentification;
    }

    public void setAccountIdentification(String accountIdentification) {
        this.accountIdentification = accountIdentification;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
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

    public String getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
