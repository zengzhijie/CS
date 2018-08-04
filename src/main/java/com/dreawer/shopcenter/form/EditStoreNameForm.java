package com.dreawer.shopcenter.form;

import com.dreawer.dream.validation.constraint.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.*;

/**
 * <CODE>EditStoreNameForm</CODE> 编辑店铺名称表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditStoreNameForm {

	@NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
	private String storeId = null;

	@NotEmpty(message=VAL_STORE_NAME_NOTEMPTY)
	@Length(min=1, max=50, message=VAL_STORE_NAME_LENGTH)
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}
