package com.dreawer.shopcenter.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <CODE>EditMerchanCategoriesForm</CODE>
 *
 * @author fenrir
 * @Date 18-4-17
 */
public class EditMerchanCategoriesForm {

    @NotEmpty(message = "店铺ID不能为空")
    private String storeId = null;

    @NotEmpty(message = "开启状态不能为空")
    private String type = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
