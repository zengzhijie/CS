package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditBusinessTypeForm</CODE> 编辑经营类型表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditBusinessTypeForm {

    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_BUS_TYP_NOTEMPTY)
    @Length(min=1, max=50, message=VAL_BUS_TYP_LENGTH)
    private String businessType = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
