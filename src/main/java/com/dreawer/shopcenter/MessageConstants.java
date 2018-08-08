package com.dreawer.shopcenter;

/**
 * <CODE>ResourceConstants</CODE> 国际化资源常量类。<br/>
 * 该类用于定义“国际化（本地化）资源”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 * @author David Dai
 * @since Dreawer 2.0
 * @version 1.0
 */
public final class MessageConstants {
    
    /**
     * 私有构造器。
     */
    private MessageConstants() {
    }
    
    /** 资源文件名 */
    public static final String FILE_MESSAGE_RESOURCE = "messageResource";
    
    // --------------------------------------------------------------------------------
    // 交互信息
    // --------------------------------------------------------------------------------

    /**  交互信息（其他异常） */
    public static final String MSG_SYSTEM_BUSY = "其他系统异常！";

    /**  交互信息（店铺信息不存在） */
    public static final String MSG_STORE_NOTFOUND = "未查询到店铺信息!";

    /**  交互信息（执照信息不存在） */
    public static final String MSG_CERT_NOTFOUND = "未查询到执照信息!";
    
    /**  交互信息（营业时间不存在） */
    public static final String MSG_BUSINESS_DAY_NOTFOUND = "营业时间不存在，请确认后重试！";
    // --------------------------------------------------------------------------------
    // 验证信息
    // --------------------------------------------------------------------------------

    /** 验证信息（店铺名字为空） */
    public static final String VAL_STORE_NAME_NOTEMPTY = "店铺名字不能为空";

    /** 验证信息（店铺名字长度有误） */
    public static final String VAL_STORE_NAME_LENGTH = "店铺长度为1-50个字符或者1-25个汉字组成";

    /** 验证信息（店铺id为空） */
    public static final String VAL_STORE_ID_NOTEMPTY = "店铺ID不能为空";

    /** 验证信息（行业类型长度有误） */
    public static final String VAL_BUS_TYP_LENGTH = "行业类型长度为1-50个字符或者1-25个汉字组成";

    /** 验证信息（行业类型为空） */
    public static final String VAL_BUS_TYP_NOTEMPTY = "行业类型不能为空";

    /** 验证信息（前脸照片为空） */
    public static final String VAL_FNT_PIC_NOTEMPTY = "未选择照片";

    /** 验证信息（前脸照片长度有误） */
    public static final String VAL_FNT_PIC_LENGTH = "照片大小不正确";

    /** 验证信息（内部照片为空） */
    public static final String VAL_INI_PIC_NOTEMPTY = "未选择照片";

    /** 验证信息（内部照片长度有误） */
    public static final String VAL_INI_PIC_LENGTH = "照片URL格式不正确";

    /** 验证信息（店铺简介为空） */
    public static final String VAL_INTRO_NOTEMPTY = "店铺简介不能为空";

    /** 验证信息（店铺简介长度有误） */
    public static final String VAL_INTRO_LENGTH = "行业类型长度由1-2000个字符或者1-1000个汉字组成";

    /** 验证信息（电话为空） */
    public static final String VAL_PHONE_NOTEMPTY = "电话不能为空";

    /** 验证信息（电话长度有误） */
    public static final String VAL_PHONE_LENGTH = "电话号码格式不正确";


    /** 验证信息（地址长度有误） */
    public static final String VAL_ADDRESS_LENGTH = "地址长度由1-500个字符或者1-250个汉字组成";

    /** 验证信息（送货时间为空） */
    public static final String VAL_DEVR_TIME_NOTEMPTY = "送货时间不能为空";

    /** 验证信息（送货时间长度有误） */
    public static final String VAL_DEVR_TIME_LENGTH = "送货时间由1-10个字符或者1-5个汉字组成";

    /** 验证信息（海报列表为空） */
    public static final String VAL_POSTERS_NOTEMPTY = "送货时间不能为空";

    /** 验证信息（海报列表长度有误） */
    public static final String VAL_POSTERS_LENGTH = "超出海报列表上传大小";

    /** 验证信息（名称长度有误） */
    public static final String VAL_NAME_LENGTH = "名称由1-50个字符或者1-25个汉字组成";

    /** 验证信息（营业执照图片为空） */
    public static final String VAL_BUS_LIC_IMG_NOTEMPTY = "营业执照图片不能为空";

    /** 验证信息（营业执照图片长度有误） */
    public static final String VAL_BUS_LIC_IMG_LENGTH = "营业执照图片URL格式不正确";


    /** 验证信息（经营范围为空） */
    public static final String VAL_BUS_SCOPE_NOTEMPTY = "经营范围不能为空";

    /** 验证信息（经营范围长度有误） */
    public static final String VAL_BUS_SCOPE_LENGTH = "经营范围由1-1000个字符或者1-500个汉字组成";

    /** 验证信息（经营时间长度有误） */
    public static final String VAL_VALIDITY_LENGTH = "经营范围由1-50个字符或者1-25个汉字组成";

    /** 验证信息（法定代表人为空） */
    public static final String VAL_LEGL_REP_NOTEMPTY = "法定代表人不能为空";

    /** 验证信息（经营范围长度有误） */
    public static final String VAL_LEGL_REP_LENGTH = "法定代表人由1-50个字符或者1-25个汉字组成";

    /** 验证信息（营业执照注册号为空） */
    public static final String VAL_REG_NUM_NOTEMPTY = "营业执照注册号不能为空";

    /** 验证信息（营业执照注册号长度有误） */
    public static final String VAL_REG_NUM_LENGTH = "营业执照注册号由1-50个字符或者1-25个汉字组成";

    /** 验证信息（许可证名称长度有误） */
    public static final String VAL_CERT_NAME_LENGTH = "许可证名称由1-50个字符或者1-25个汉字组成";

    /** 验证信息（营业执照图片长度有误） */
    public static final String VAL_CERT_IMAGE_LENGTH = "营业执照图片URL长度不正确";

    /** 验证信息（许可证编号长度有误） */
    public static final String VAL_CERT_NUM_LENGTH = "许可证编号由1-60个字符组成";

    /** 验证信息（有效期长度有误） */
    public static final String VAL_CERT_VAL_LENGTH = "有效期由1-50个字符或者1-25个汉字组成";

    /** 验证信息（证件类型不能为空） */
    public static final String VAL_CERT_TYPE_NOTEMPTY = "证件类型不能为空";

    /** 对象ID号为空 */
    public static final String VAL_OBJECT_ID_NOT_EMPTY = "未指定对象ID号，请检查并重试";

    /** 对象ID号长度太短 */
    public static final String VAL_OBJECT_ID_LENGTH_MIN = VAL_OBJECT_ID_NOT_EMPTY;

    /** 对象ID号为空 */
    public static final String VAL_OBJECT_CATEGORY_NOT_EMPTY = "未指定对象分类，请检查并重试";

    /** 验证信息（品牌ID为空） */
    public static final String VAL_BRAND_ID_EMPTY = "品牌ID不能为空";

    /** 验证信息（企业ID为空） */
    public static final String VAL_ENTERPRISE_ID_EMPTY = "企业ID不能为空";

    /** 验证信息（appid为空） */
    public static final String VAL_APP_ID_EMPTY = "appid不能为空";

    /** 验证信息（产品ID为空） */
    public static final String VAL_PRODUCT_ID_EMPTY = "产品ID不能为空";

    /** 验证信息（产品名称为空） */
    public static final String VAL_PRODUCT_NAME_EMPTY = "名称为1~120个字符或1~60汉字";

    /** 验证信息（产品logo长度不合规） */
    public static final String VAL_PRODUCT_LOGO_LENGTH = "LOGO为1张图片";

    /** 验证信息（产品图片长度不合规） */
    public static final String VAL_PRODUCT_IMAGE_LENGTH = "图片为1~5张";

    /** 验证信息（产品slogan长度不合规） */
    public static final String VAL_PRODUCT_SLOGAN_LENGTH = "宣传语为1~100字符或1~50汉字";

    /** 验证信息（产品描述长度不合规） */
    public static final String VAL_PRODUCT_DESC_LENGTH = "描述为1~400字符或1~200汉字";

    /** 验证信息（值不能为空） */
    public static final String VAL_NAME_NOTEMPTY = "名称不能为空";

    /** 验证信息（值不能为空） */
    public static final String VAL_IMAGE_NOTEMPTY = "图片不能为空";

    /** 验证信息（值不能为空） */
    public static final String VAL_SCOPE_NOTEMPTY = "经营范围不能为空";

    /** 验证信息（值不能为空） */
    public static final String VAL_LEGAL_NOTEMPTY = "法人代表不能为空";

    /** 验证信息（值不能为空） */
    public static final String VAL_NUM_NOTEMPTY = "证件编号不能为空";

    /** 验证信息（值不能为空） */
    public static final String VAL_ADDRESS_NOTEMPTY = "地址不能为空";

    /** 验证信息 (收货人不能为空） */
    public static final String VAL_CONSIGNEE_NOTEMPTY = "收货人不能为空";

    /** 验证信息 (发货地值不能为空） */
    public static final String VAL_SHIPPING_ADDRESS_NOTEMPTY = "发货地址不能为空";

    /** 验证信息 (详细地址不能为空） */
    public static final String VAL_DETAIL_ADDRESS_NOTEMPTY = "详细地址不能为空";

    /** 验证信息 (联系方式不能为空） */
    public static final String VAL_CONTACT_METHOD = "联系方式不能为空";

    /** 验证信息 (邮编不能为空） */
    public static final String VAL_POSTCODE_NOTEMPTY = "邮编不能为空";

    /** 验证信息 (地址id不能为空） */
    public static final String VAL_ADDRESS_ID_NOTEMPTY = "地址id不能为空";

    /** 验证信息 (邮编格式不正确) **/
    public static final String VAL_POSTCODE_WRONG = "邮编格式应为6位数字";

    /** 验证信息 (长度不合规） */
    public static final String VAL_CONSIGNEE_LENTH = "收货人长度不合规范,应为1-20个字符或1-10个汉字";

    /** 验证信息 (长度不合规） */
    public static final String VAL_CONTACT_LENGTH = "联系方式长度不合规范,应为1-100个字符或1-50个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_NAME_LENTH = "名称长度不合规，应为1-50个字符或1-25个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_SHORTNAME_LENTH = "简称长度不合规，应为1-30个字符或1-15个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_LOGO_LENTH = "logo长度不合规，应为1-200个字符";

    /** 验证信息（长度不合规） */
    public static final String VAL_OFFICIAL_SITE_LENGTH = "官方网站长度不合规，应为1-200个字符";

    /** 验证信息（长度不合规） */
    public static final String VAL_INTRO_LENTH = "简介长度不合规，应为1-400个字符或1-200个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_IMAGE_LENTH = "图片长度不合规，应为1-255个字符";

    /** 验证信息（长度不合规） */
    public static final String VAL_SCOPE_LENTH = "经营范围长度不合规，应为1-1000个字符或1-500个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_VAL_LENTH = "有效期长度不合规，应为1-50个字符或1-25个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_LEGAL_LENTH = "法人长度不合规，应为1-50个字符或1-25个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_NUM_LENTH = "证件编号长度不合规，应为1-60个字符";

    /** 验证信息（长度不合规） */
    public static final String VAL_ADDRESS_LENTH = "地址长度不合规，应为1-500个字符或1-250个汉字";

    /** 验证信息（长度不合规） */
    public static final String VAL_POSTERS_LENTH = "海报长度不合规，应为1-1200个字符";

    /** 验证信息（长度不合规） */
    public static final String VAL_BOOT_IMG_LENTH = "启动图片长度不合规，应为2-200个字符";

    /** 验证信息（email格式不对） */
    public static final String VAL_EMAIL_WRONG = "邮箱格式不正确";

    /** 验证信息（手机号格式不对） */
    public static final String VAL_PHONE_WRONG = "座机格式不正确";

    /** 验证信息（手机号格式不对） */
    public static final String VAL_MOBAIL_WRONG = "手机号格式不正确";
    
    /** 验证信息（手机和座机重复设置） */
    public static final String VAL_PHONE_DUMP = "手机和座机不能重复设置";

    /** 验证信息（编号格式不对） */
    public static final String VAL_NUM_TYPE_WRONG = "编号格式不正确";

    /** 验证信息（商品包装不能为空） */
    public static final String VAL_IS_PACKING_NOTEMPTY = "商品包装不能为空!";

    /** 验证信息（优惠ID为空） */
    public static final String VAL_COUPON_ID_NOTEMPTY = "优惠劵ID不能为空!";

    /** 验证信息（SKUID为空） */
    public static final String VAL_SKU_ID_NOTEMPTY = "skuID不能为空!";

    /** 验证信息（数量为空） */
    public static final String VAL_QUANTITY_NOTEMPTY = "商品数量不能为空!";

    /** 品牌不存在 */
    public static final String MSG_BRAND_NOTFOUND = "品牌不存在！";

    /** 产品不存在 */
    public static final String MSG_PRODUCT_NOTFOUND = "产品不存在！";

    /** 系统异常 */
    public static final String MSG_SYS_BUSY = "系统异常，请稍后再试";

    /** 应用不存在 */
    public static final String MSG_APP_NULL = "该应用不存在";

    /** 地址不存在 */
    public static final String MSG_ADDRESS_NOTFOUND = "该地址不存在";

    /** 地址不存在 */
    public static final String MSG_ADDRESS_DELETE_ERROR = "只有一条地址,无法删除";

    /** 企业已存在 */
    public static final String MSG_ENTERPRISE_NULL = "该企业不存在";

    /** 企业已存在 */
    public static final String MSG_ENTERPRISE_EXISTS = "该企业已存在";

    /** 应用不存在 */
    public static final String MSG_ENTERPRISE_NAME_EXISTS = "该企业名称已存在";

    /** 验证码为空 */
    public static final String MSG_VALUE_NULL = "验证码不能为空";

    /** 验证码错误 */
    public static final String MSG_VALUE_ERROR = "验证码错误";

    /** 营业执照信息已存在 */
    public static final String MSG_LICENSE_EXISTS = "营业执照信息已存在";

    /** 营业执照信息不存在 */
    public static final String MSG_LICENSE_NULL = "营业执照信息不存在";

    /** 证件信息不存在 */
    public static final String MSG_CERTIFICATE_NULL = "证件信息不存在";

    /** 播放时长不为空 */
    public static final String MSG_BOOT_TIME_NULL = "播放时长不能为空";

    /** 播放时长大于3秒 */
    public static final String MSG_BOOT_TIME_LENGH = "播放时长不能大于3秒";

    /** 商品ID不能为空 */
    public static final String VAL_MERCHANDISE_ID_NOTEMPTY = "商品ID不能为空";

    /** 标题不能为空 */
    public static final String VAL_TITLE_NOTEMPTY = "标题不能为空";

    /** 标题长度不正确 */
    public static final String VAL_TITLE_LENGTH = "标题最大为20个字";

    /** 跳转类型不能为空 */
    public static final String VAL_TYPE_NOTEMPTY = "跳转类型不能为空";


    /** 跳转ID不能为空 */
    public static final String VAL_REDIRECTID_NOTEMPTY = "跳转ID不能为空";

    /** 展示首页不能为空 */
    public static final String VAL_DISPLAY_NOTEMPTY = "展示首页不能为空";

    /** 跳转目标不能为空不能为空 */
    public static final String VAL_REDIRECT_INFO_NOTEMPTY = "跳转目标不能为空";

    // --------------------------------------------------------------------------------
    // 警告信息
    // --------------------------------------------------------------------------------
    
    /** 警告信息（专题未找到） */
    public static final String WARN_TOPIC_NOTFOUND = "warn.topic.notfound";
    
    /** 警告信息（服务范围未找到） */
    public static final String WARN_CATEGORY_NOTFOUND = "warn.category.notfound";
    
    /** 警告信息（应用未找到） */
    public static final String WARN_APP_NOTFOUND = "warn.app.notfound";
    
    /** 警告信息（应用重复） */
    public static final String WARN_APP_NAME_DUPLUN = "warn.app.name.duplun";
    
    // --------------------------------------------------------------------------------
    // 错误信息
    // --------------------------------------------------------------------------------
    
    /** 错误信息（其他异常） */
    public static final String ERR_OTHER = "err.other";
    
}
