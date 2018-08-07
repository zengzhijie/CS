package com.dreawer.shopcenter.domain;

import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;

/**
 * <CODE>Address</CODE>
 * 地址实体类
 * @author fenrir
 * @Date 17-12-25
 */
public class Address extends BaseDomain {

    private static final long serialVersionUID = -3040562912070693905L;

    private String consignee = null;

    private String contact = null;

    private String shippingAddress = null;

    private String address = null;

    private String postcode = null;

    private Boolean isDefault = null;

    private String storeId = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    private String userId = null; // 创建者

    private Timestamp createTime = null; // 创建时间

    private String updaterId = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


}
