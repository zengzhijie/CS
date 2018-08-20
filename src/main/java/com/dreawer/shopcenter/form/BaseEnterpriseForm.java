package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

import static com.dreawer.shopcenter.MessageConstants.*;

@ApiModel(value = "基本企业表单")
@Data
public class BaseEnterpriseForm {

	@ApiModelProperty(value = "企业名称")
    @Length(min=1, max=50, message=VAL_NAME_LENTH)
	private String name = null;

	@ApiModelProperty(value = "小程序名称")
    @Length(min=1, max=30, message=VAL_SHORTNAME_LENTH)
	private String appName = null;

    @Length(min=1, max=200, message=VAL_LOGO_LENTH)
	private String logo = null;

    @ApiModelProperty(value = "简介")
    @Length(min=1, max=400, message=VAL_INTRO_LENTH)
	private String intro = null;

    @ApiModelProperty(value = "位置坐标")
	private String coordinate = null;

    @ApiModelProperty(value = "企业详情")
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

	@ApiModelProperty(value = "官网URL")
	@Length(min = 1,max = 200,message = VAL_OFFICIAL_SITE_LENGTH)
	private String url = null; //官方网站

	@ApiModelProperty(value = "营业执照URL")
	@Length(min = 1,max = 255,message = VAL_BUS_LIC_IMG_LENGTH)
	private String businessLicense = null;

	@ApiModelProperty(value = "特许证件URL")
	@Length(min = 1,max = 255,message = VAL_CERT_IMAGE_LENGTH)
	private String certificate = null;

	@ApiModelProperty(value = "特许证件类型")
	@Length(min = 1,max = 50,message = VAL_CERT_TYPE_LENGTH)
	private String certType = null;


}
