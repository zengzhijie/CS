package com.dreawer.shopcenter.domain;

import com.dreawer.dream.domain.BaseDomain;
import com.dreawer.user.domain.User;

import java.sql.Timestamp;


public class BusinessLicense extends BaseDomain{

    private static final long serialVersionUID = 7154170738721391613L;

    private String enterpriseId = null; //店铺序列号

    private String name = null; //营业执照名称

    private String image = null; //营业执照图片

    private String businessScope = null; //经营范围

    private String validityType = null; //经营时间类型

    private String validity = null; //经营时间

    private String legalRepresentative = null; //法定代表人

    private String registrationNumber = null; //营业执照注册号

    private String address = null; //地址

    private User creater = null; // 创建者

    private Timestamp createTime = null; // 创建时间

    private User updater = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    public String getValidityType() {
        return validityType;
    }

    public void setValidityType(String validityType) {
        this.validityType = validityType;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getCreater() {
        return creater;
    }

    public void setCreater(User creater) {
        this.creater = creater;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getUpdater() {
        return updater;
    }

    public void setUpdater(User updater) {
        this.updater = updater;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
}
