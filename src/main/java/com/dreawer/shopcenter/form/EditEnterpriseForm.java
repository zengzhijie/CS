package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_BOOT_IMG_LENTH;
import static com.dreawer.shopcenter.MessageConstants.VAL_OBJECT_ID_NOT_EMPTY;

public class EditEnterpriseForm extends BaseEnterpriseForm{
	
	@NotEmpty(message=VAL_OBJECT_ID_NOT_EMPTY)
	private String id = null;

	private String value = null;

    @Length(min=2, max=200, message=VAL_BOOT_IMG_LENTH)
	private String bootImage = null;
	
	private Integer bootTime = null;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	
}
