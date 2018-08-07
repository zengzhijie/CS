package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditPhoneForm</CODE> 编辑店铺电话表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditPhoneForm {
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_PHONE_NOTEMPTY)
    @Pattern(regexp = "(^\\d{3}-\\d{8}$|^\\d{4}-\\d{7,8})$|^(\\d{8})$|(^\\d{3}\\d{8}|\\d{4}\\d{7,8}$)",
            message=VAL_PHONE_LENGTH)
    private String phone = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
