package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.VAL_OBJECT_ID_NOT_EMPTY;

public class EditBusinessLicenseForm extends BaseBusinessLicenseForm {

	
    @NotEmpty(message = VAL_OBJECT_ID_NOT_EMPTY)
    private String id = null; //店铺序列号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
