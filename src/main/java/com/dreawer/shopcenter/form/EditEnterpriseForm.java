package com.dreawer.shopcenter.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.VAL_BOOT_IMG_LENTH;
import static com.dreawer.shopcenter.MessageConstants.VAL_OBJECT_ID_NOT_EMPTY;

@Data
public class EditEnterpriseForm extends BaseEnterpriseForm{
	
	@NotEmpty(message=VAL_OBJECT_ID_NOT_EMPTY)
	private String id = null;

	private String value = null;

    @Length(min=2, max=200, message=VAL_BOOT_IMG_LENTH)
	private String bootImage = null;
	
	private Integer bootTime = null;

	private String province = null;

	private String city = null;

	private String area = null;

	
}
