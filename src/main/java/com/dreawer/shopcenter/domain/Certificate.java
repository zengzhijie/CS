package com.dreawer.shopcenter.domain;

import com.dreawer.dream.domain.BaseDomain;
import com.dreawer.user.domain.User;

import java.sql.Timestamp;

/**
 * <CODE>Certificate</CODE> 其他证件实体类。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class Certificate extends BaseDomain{

    private static final long serialVersionUID = -2105923816019595718L;

    private String enterpriseId = null;  //店铺id

    private String name = null;  //许可证类型名称

    private String image = null;  //营业执照图片

    private String number = null;  //营业执照编号

    private String validityType = null;  //类型(LONG_TERM-长期、REGULARLY-定期)

    private String validity = null;  //有效期

    private String address = null;  //地址

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
