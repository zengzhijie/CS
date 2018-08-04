package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.VAL_ENTERPRISE_ID_EMPTY;

public class AddBusinessLicenseForm extends BaseBusinessLicenseForm {

    @NotEmpty(message = VAL_ENTERPRISE_ID_EMPTY)
    private String enterpriseId = null; //店铺序列号

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
}
