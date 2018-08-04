package com.dreawer.shopcenter.form;

import com.dreawer.dream.validation.constraint.Length;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

import static com.dreawer.retail.MessageConstants.*;


public class BaseEnterpriseForm {
	
    @Length(min=1, max=50, message=VAL_NAME_LENTH)
	private String name = null;
	
    @Length(min=1, max=30, message=VAL_SHORTNAME_LENTH)
	private String shortname = null;
	
    @Length(min=1, max=200, message=VAL_LOGO_LENTH)
	private String logo = null;
	
    @Length(min=1, max=400, message=VAL_INTRO_LENTH)
	private String intro = null;

	private String coordinate = null; // 位置坐标

    @Length(min=1, max=500, message=VAL_ADDRESS_LENTH)
	private String detail = null;
	
	@Email(message=VAL_EMAIL_WRONG)
	private String email = null;
	
	//@Pattern(regexp="^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$",
	//		message=VAL_PHONE_WRONG)
	private String phone = null;
	
	@Pattern(regexp="^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$",
			message=VAL_PHONE_WRONG)
	private String mobile = null;
	
    @Length(min=1, max=1200, message=VAL_POSTERS_LENTH)
	private String posters = null;

	@Pattern(regexp="^1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\\d{8}$",
			message=VAL_PHONE_WRONG)
	private String telephone = null;

	@Length(min = 1,max = 200,message = VAL_OFFICIAL_SITE_LENGTH)
	private String url = null; //官方网站

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
}
