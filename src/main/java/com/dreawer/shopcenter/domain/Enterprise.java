package com.dreawer.shopcenter.domain;

import com.dreawer.dream.domain.BaseDomain;
import com.dreawer.user.domain.User;

import java.sql.Timestamp;

public class Enterprise extends BaseDomain{

	private static final long serialVersionUID = 8940480431090127598L;

	private String appid = null;
	
	private String category = null;

	private String name = null;
	
	private String shortname = null;
	
	private String logo = null;
	
	private String intro = null;
	
	private String coordinate = null; // 位置坐标

	private String province = null;
	
	private String city = null;
	
	private String area = null;

	private String detail = null;
	
	private String email = null;

	private String phone = null;

	private String mobile = null;

	private String bootImage = null;

	private Integer bootTime = null;

	private String posters = null;

	private String telephone = null;

	private String merchanCategories = null;

	private Boolean memberRegisterPort = true; //是否在商品详情显示会员注册通道

	private String growthRules = null; //会员成长值规则JSON

	private String url = null;

	public String getMerchanCategories() {
		return merchanCategories;
	}

	public void setMerchanCategories(String merchanCategories) {
		this.merchanCategories = merchanCategories;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGrowthRules() {
		return growthRules;
	}

	public void setGrowthRules(String growthRules) {
		this.growthRules = growthRules;
	}

	public Boolean getMemberRegisterPort() {
		return memberRegisterPort;
	}

	public void setMemberRegisterPort(Boolean memberRegisterPort) {
		this.memberRegisterPort = memberRegisterPort;
	}

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

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosters() {
		return posters;
	}

	public void setPosters(String posters) {
		this.posters = posters;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBootImage() {
		return bootImage;
	}

	public void setBootImage(String bootImage) {
		this.bootImage = bootImage;
	}

	public Integer getBootTime() {
		return bootTime;
	}

	public void setBootTime(Integer bootTime) {
		this.bootTime = bootTime;
	}

	@Override
	public String toString() {
		return "Enterprise{" +
				"appid='" + appid + '\'' +
				", category='" + category + '\'' +
				", name='" + name + '\'' +
				", shortname='" + shortname + '\'' +
				", logo='" + logo + '\'' +
				", intro='" + intro + '\'' +
				", coordinate='" + coordinate + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", area='" + area + '\'' +
				", detail='" + detail + '\'' +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", mobile='" + mobile + '\'' +
				", bootImage='" + bootImage + '\'' +
				", bootTime=" + bootTime +
				", posters='" + posters + '\'' +
				", telephone='" + telephone + '\'' +
				", url='" + url + '\'' +
				", creater=" + creater +
				", createTime=" + createTime +
				", updater=" + updater +
				", updateTime=" + updateTime +
				'}';
	}
}
