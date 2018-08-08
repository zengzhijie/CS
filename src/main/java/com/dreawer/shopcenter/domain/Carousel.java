package com.dreawer.shopcenter.domain;

import com.dreawer.domain.BaseDomain;
import com.dreawer.shopcenter.lang.RedirectType;

import java.sql.Timestamp;

/**
 * <CODE>Carousel</CODE>
 * 轮播图实体类
 * @author fenrir
 * @Date 18-8-7
 */
public class Carousel extends BaseDomain {

    private static final long serialVersionUID = 7154170738721391613L;

    private String title; //标题

    private String storeId; //店铺ID

    private RedirectType type; //跳转类型

    private String redirectId; //跳转链接

    private String redirectInfo; //跳转目标

    private String image; //图片链接

    private Boolean display; //首页展示

    private Integer sequence; //顺序

    private String userId = null; // 创建者

    private Timestamp createTime = null; // 创建时间

    private String updaterId = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getRedirectInfo() {
        return redirectInfo;
    }

    public void setRedirectInfo(String redirectInfo) {
        this.redirectInfo = redirectInfo;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RedirectType getType() {
        return type;
    }

    public void setType(RedirectType type) {
        this.type = type;
    }

    public String getRedirectId() {
        return redirectId;
    }

    public void setRedirectId(String redirectId) {
        this.redirectId = redirectId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean display) {
        this.display = display;
    }
}
