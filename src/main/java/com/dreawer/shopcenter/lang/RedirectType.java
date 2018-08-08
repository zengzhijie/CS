package com.dreawer.shopcenter.lang;

public enum RedirectType {


    /** 活动 */
    PROMOTION,

    /** 商品 */
    GOODS;

    /**
     * 获取跳转类型。
     * @param name 状态名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static RedirectType get(String name) {
        for (RedirectType status : RedirectType.values()) {
            if (status.toString().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
