package com.dreawer.shopcenter.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_APP_ID_EMPTY;

@ApiModel(value = "添加企业表单")
public class AddEnterpriseForm extends BaseEnterpriseForm{

	@ApiModelProperty(value = "小程序ID")
	@NotEmpty(message=VAL_APP_ID_EMPTY)
	private String appid = null;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

}
