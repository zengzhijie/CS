package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>EditPostersForm</CODE> 编辑店铺海报列表表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditPostersForm {
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_POSTERS_NOTEMPTY)
    @Length(min=1, max=1300, message=VAL_POSTERS_LENGTH)
    private String posters = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPosters() {
        return posters;
    }

    public void setPosters(String posters) {
        this.posters = posters;
    }
}
