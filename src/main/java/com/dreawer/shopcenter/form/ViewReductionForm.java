package com.dreawer.shopcenter.form;

import javax.validation.constraints.NotNull;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>ViewReductionForm</CODE>
 *
 * @author fenrir
 * @Date 18-1-25
 */
public class ViewReductionForm {

    @NotNull(message = VAL_SKU_ID_NOTEMPTY)
    String skuId = null; //商品skuID

    @NotNull(message = VAL_QUANTITY_NOTEMPTY)
    String quantity = null; //数量

    @NotNull(message = VAL_IS_PACKING_NOTEMPTY)
    String isPacking = null; //是否包

    String promotionId = null; //活动ID

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getIsPacking() {
        return isPacking;
    }

    public void setIsPacking(String isPacking) {
        this.isPacking = isPacking;
    }
}
