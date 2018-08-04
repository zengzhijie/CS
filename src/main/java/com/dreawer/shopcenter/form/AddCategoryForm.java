package com.dreawer.shopcenter.form;

import javax.validation.constraints.NotNull;

/**
 * <CODE>AddCategoryForm</CODE>
 *
 * @author fenrir
 * @Date 18-3-30
 */
public class AddCategoryForm {

    @NotNull(message = "类目列表不能为空")
    private String ids; //类目列表

    @NotNull(message = "店铺ID不能为空")
    private String storeId; //店铺ID


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
