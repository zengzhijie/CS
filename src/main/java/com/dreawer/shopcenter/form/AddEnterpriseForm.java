package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.VAL_APP_ID_EMPTY;

public class AddEnterpriseForm extends BaseEnterpriseForm{

	@NotEmpty(message=VAL_APP_ID_EMPTY)
	private String appid = null;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

}
