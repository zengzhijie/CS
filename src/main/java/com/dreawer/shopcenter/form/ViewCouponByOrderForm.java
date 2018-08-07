package com.dreawer.shopcenter.form;

import javax.validation.constraints.NotNull;

import static com.dreawer.shopcenter.MessageConstants.*;

/**
 * <CODE>ViewCouponByOrderForm</CODE>
 * 查询订单可用优惠劵列表
 * @author fenrir
 * @Date 18-1-25
 */
public class ViewCouponByOrderForm {

    @NotNull(message = VAL_MERCHANDISE_ID_NOTEMPTY)
    String merchandiseId = null; //商品ID

    @NotNull(message = VAL_SKU_ID_NOTEMPTY)
    String skuId = null; //skuID

    @NotNull(message = VAL_QUANTITY_NOTEMPTY)
    String quantity = null; //数量

    String isPacking = null; //是否包装

    String promotionId = null; //活动ID

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getMerchandiseId() {
        return merchandiseId;
    }

    public void setMerchandiseId(String merchandiseId) {
        this.merchandiseId = merchandiseId;
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

