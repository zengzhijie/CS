package com.dreawer.shopcenter.lang;

public enum OperatingStatus {
	
	/** 营业 */
	OPEN,
    
    /** 非营业 */
	CLOSED;
	
    /**
     * 获取营业状态。
     * @param name 状态名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static OperatingStatus get(String name) {
        for (OperatingStatus status : OperatingStatus.values()) {
            if (status.toString().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
