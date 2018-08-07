package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditIntroForm</CODE> 编辑店铺简介表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditIntroForm {
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_INTRO_NOTEMPTY)
    @Length(min=1, max=2000, message=VAL_INTRO_LENGTH)
    private String intro = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
