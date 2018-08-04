package com.dreawer.shopcenter.form;

import com.dreawer.coupon.domain.UserUnusedCoupon;

import java.math.BigDecimal;

/**
 * <CODE>ViewCountCouponPair</CODE>
 * 减扣金额和优惠劵键值对
 * @author fenrir
 * @Date 18-1-27
 */
public class ViewCountCouponPair {

    private BigDecimal count = null;

    private UserUnusedCoupon coupon;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public UserUnusedCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(UserUnusedCoupon coupon) {
        this.coupon = coupon;
    }
}
