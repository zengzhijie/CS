package com.dreawer.shopcenter.form;

import com.dreawer.dream.validation.constraint.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static com.dreawer.retail.MessageConstants.*;

/**
 * <CODE>EditDeliveryTimeForm</CODE> 编辑送货时间表单。
 * @author lyan
 * @since Dreawer 2.0
 * @version 1.0
 */
public class EditDeliveryTimeForm {
    @NotEmpty(message=VAL_STORE_ID_NOTEMPTY)
    private String storeId = null;

    @NotEmpty(message=VAL_DEVR_TIME_NOTEMPTY)
    @Length(min=1, max=10, message=VAL_DEVR_TIME_LENGTH)
    private String deliveryTime = null;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}
