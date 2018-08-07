package com.dreawer.shopcenter;

/**
 * <CODE>RequestConstants</CODE> 控制器层常量类。
 * 该类用于定义“请求链接”和“页面地址”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 *
 * @author David Dai
 * @version 1.0
 * @since Dreawer 1.0
 */
public final class ControllerConstants {

    /**
     * 订单控制器
     */
    public static final String STORE_CONTROLLER = "storeController";


    // --------------------------------------------------------------------------------
    // 控制器
    // --------------------------------------------------------------------------------
    /**
     * 营业执照控制器
     */
    public static final String BUS_LIC_CONTROLLER = "businessLicenseController";
    /**
     * 评论控制器
     */
    public static final String COMMON_CONTROLLER = "commonController";
    /**
     * 评论控制器
     */
    public static final String ORDER_HANDLE_CONTROLLER = "orderHandleController";
    /**
     * 企业控制器
     */
    public static final String ENTERPRISE_CONTROLLER = "enterpriseController";

    /**
     * 地址控制器
     */
    public static final String ADDRESS_CONTROLLER = "addressController";
    /**
     * 品牌控制器
     */
    public static final String BRAND_CONTROLLER = "brandController";
    /**
     * 产品控制器
     */
    public static final String PRODUCT_CONTROLLER = "productController";
    /**
     * 地图控制器
     */
    public static final String MAP_CONTROLLER = "mapController";
    /**
     * 301转向（永久性重定向）
     */
    public static final String REDIRECT_301 = "redirectPermanent:";


    // --------------------------------------------------------------------------------
    // 请求地址
    // --------------------------------------------------------------------------------
    /**
     * 302转向（临时转向）
     */
    public static final String REDIRECT_302 = "redirect:";
    /**
     * 跳转（不释放原有参数）
     */
    public static final String FORWARD = "forward:";
    /**
     * 请求“店铺”
     */
    public static final String REQ_STORE = "/store";
    /**
     * 请求“评论”
     */
    public static final String REQ_COMMON = "/common";
    /**
     * 请求“添加”
     */
    public static final String REQ_ADD = "/add";
    /**
     * 请求“浏览”
     */
    public static final String REQ_VIEW = "/view";

    /**
     * 请求“单件”
     */
    public static final String REQ_ONE = "/one";

    /**
     * 请求“店铺信息”
     */
    public static final String REQ_INFO = "/info";
    /**
     * 请求“修改店铺名称”
     */
    public static final String REQ_NAME_EDIT = "/editName";
    /**
     * 请求“修改行业类型”
     */
    public static final String REQ_BUS_TYP_EDIT = "/editBusTyp";
    /**
     * 请求“修改门脸照片”
     */
    public static final String REQ_FNT_PIC_EDIT = "/editFrontPic";
    /**
     * 请求“修改店内照片”
     */
    public static final String REQ_INI_PIC_EDIT = "/editInteriorPic";
    /**
     * 请求“修改店铺简介”
     */
    public static final String REQ_INTRO_EDIT = "/editIntro";
    /**
     * 请求“修改店铺地址”
     */
    public static final String REQ_ADDRESS_EDIT = "/editAddress";
    /**
     * 请求“修改店铺电话”
     */
    public static final String REQ_PHONE_EDIT = "/editPhone";
    /**
     * 请求“修改送货时间”
     */
    public static final String REQ_DEVR_TIME_EDIT = "/editDeliveryTime";
    /**
     * 请求“修改海报列表
     */
    public static final String REQ_POSTERS_EDIT = "/editPosters";
    /**
     * 请求“详情”
     */
    public static final String REQ_DETAIL = "/detail";
    /**
     * 请求“查询营业执照信息”
     */
    public static final String REQ_BUS_LIC = "/busLic";

    /**
     * 请求“地址”
     */
    public static final String REQ_ADDRESS = "/address";


    /**
     * 请求“修改营业执照信息”
     */
    public static final String REQ_BUS_LIC_EDIT = "/editBusLic";
    /**
     * 请求“查询其他证件信息”
     */
    public static final String REQ_CERT = "/getCertificate";
    /**
     * 请求“修改其他证件信息”
     */
    public static final String REQ_CERT_EDIT = "/editCertificate";
    /**
     * 请求“查询店铺营业时间”
     */
    public static final String REQ_BUS_HRS = "/getBusHrs";
    /**
     * 请求“编辑店铺营业时间”
     */
    public static final String REQ_BUS_HRS_EDIT = "/editBusHrs";
    /**
     * 请求“营业时间”
     */
    public static final String REQ_BUSINESS_DAY = "/businessDay";
    /**
     * 请求“详情”
     */
    public static final String REQ_LIST = "/list";
    /**
     * 请求“编辑”
     */
    public static final String REQ_EDIT = "/edit";

    /**
     * 请求“删除”
     */
    public static final String REQ_DELETE = "/delete";

    /**
     * 请求“设置为默认”
     */
    public static final String REQ_SET_DEFAULT = "/setDefault";

    /**
     * 请求“腾讯地图关键字搜索”
     */
    public static final String REQ_TXMAP_SUGGESTION = "/txmap/suggestion";
    /**
     * 请求“腾讯地图查询”
     */
    public static final String REQ_TXMAP_QUERY = "/txmap/query";
    /**
     * 请求“腾讯地图坐标查询”
     */
    public static final String REQ_TXMAP_LOCATION = "/txmap/location";
    /**
     * 请求“腾讯地图地址查询”
     */
    public static final String REQ_TXMAP_ADDRESS = "/txmap/address";

    /** 请求“返回折扣金额” */
    public static final String REQ_REDUCTION_AMOUNT = "/reductionAmount";

    /** 请求“优先推荐优惠劵” */
    public static final String REQ_OPTIMUM_COUPON = "/optimumCoupon";

    /** app详情url **/
    public static final String APP_DETAIL = "/app/detail";

    /** 刷新token **/
    public static final String REFRESH_TOKEN = "/thirdPart/refreshToken";

    /**
     * 用户请求 URL
     */
    public static final String REQ_URL = "requestUrl";

    // --------------------------------------------------------------------------------
    // 其他常量
    // --------------------------------------------------------------------------------
    /**
     * 错误信息
     */
    public static final String ERRORS = "errors";
    /**
     * 错误信息
     */
    public static final String ERROR = "error";

    /** 腾讯地图关键字搜索URL */
    public static final String URL_TXMAP_SUGGESTION  = "https://apis.map.qq.com/ws/place/v1/suggestion/";

    /** 腾讯地图详细坐标查询URL */
    public static final String URL_TXMAP_LOCATION  = "https://apis.map.qq.com/ws/geocoder/v1/";

    /** 腾讯密匙 */
    public static final String TXMAP_KEY = "3PHBZ-XT2WF-RBCJD-JMP6T-7QL5J-VDFBH";

    /**
     * 私有构造器。
     */
    private ControllerConstants() {
    }

}
