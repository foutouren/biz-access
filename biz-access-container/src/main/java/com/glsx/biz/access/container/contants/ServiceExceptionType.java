package com.glsx.biz.access.container.contants;

public class ServiceExceptionType {

	public static final String CODE_PARAMETER = "1000";
	public static final String MSG_PARAMETER = "参数错误";

	public static final String CODE_USER_BINDINGED = "4001";
	public static final String MSG_USER_BINDINGED = "用户已绑定";

	public static final String CODE_ACCOUNT_BINDINGED_DEVICE_TYPE = "4002";
	public static final String MSG_ACCOUNT_BINDINGED_DEVICE_TYPE = "账号已绑定了该设备类型";

	public static final String CODE_USER_MOBILE_NOT_NULL = "4003";
	public static final String MSG_USER_MOBILE_NOT_NULL = "手机号不能为空";

	public static final String CODE_MOBILE_EXIST = "4004";
	public static final String MSG_MOBILE_EXIST = "手机号已使用";

	public static final String CODE_USER_NOT_EXIST = "4005";
	public static final String MSG_USER_NOT_EXIST = "用户不存在";

	public static final String CODE_USER_EMAIL_NOT_NULL = "4006";
	public static final String MSG_USER_EMAIL_NOT_NULL = "邮箱不能为空";

	public static final String CODE_EMAIL_EXIST = "4007";
	public static final String MSG_EMAIL_EXIST = "邮箱已使用";

	public static final String CODE_DEVICE_SN_NOT_NULL = "4008";
	public static final String MSG_DEVICE_SN_NOT_NULL = "SN不能为空";

	public static final String CODE_PHY_DEVICE_NOT_EXIST = "4009";
	public static final String MSG_PHY_DEVICE_NOT_EXIST = "物理设备不存在";

	public static final String CODE_PHY_DEVICE_ACTIVED = "4010";
	public static final String MSG_PHY_DEVICE_ACTIVED = "物理设备已激活";

	public static final String CODE_PHY_DEVICE_NOT_PASSED = "4011";
	public static final String MSG_PHY_DEVICE_NOT_PASSED = "物理设备不存在或验证码错误";

	public static final String CODE_CITY_EXIST = "4012";
	public static final String MSG_CITY_EXIST = "城市已存在";

	public static final String CODE_PROVINCE_EXIST = "4013";
	public static final String MSG_PROVINCE_EXIST = "省份已存在";

	public static final String CODE_USER_PASSORD_ERROR = "4014";
	public static final String MSG_USER_PASSORD_ERROR = "密码不正确";

	public static final String CODE_ADD_PHY_DEVICE_ID = "4015";
	public static final String MSG_ADD_PHY_DEVICE_ID = "请选择设备再进行导入";

	public static final String CODE_ADD_PHY_DEVICE_PACKAGEID_ISNULL = "4016";
	public static final String MSG_ADD_PHY_DEVICE_PACKAGEID_ISNULL = "未绑定套餐";

	public static final String CODE_ADD_PHY_DEVICE_PACKAGEID_NOTEXIST = "4017";
	public static final String MSG_ADD_PHY_DEVICE_PACKAGEID_NOTEXIST = "绑定的套餐不存在";

	public static final String CODE_ADD_PHY_DEVICE_ERROR = "4018";
	public static final String MSG_ADD_PHY_DEVICE_ERROR = "导入异常";

	public static final String CODE_USER_DB_ERROR = "4019";
	public static final String MSG_USER_DB_ERROR = "数据库异常";

	public static final String CODE_USER_DD_ERROR = "4020";
	public static final String MSG_USER_DD_ERROR = "生成嘀嘀号异常";

	public static final String CODE_USER_SOURCE_NULL = "4021";
	public static final String MSG_USER_SOURCE_NULL = "生成嘀嘀号传来参数为空";

	public static final String CODE_PHY_DEVICE_NOT_PATCH = "4022";
	public static final String MSG_PHY_DEVICE_NOT_PATCH = "设备类型不匹配";

	public static final String CODE_ACCOUNT_NOT_EXIST = "4023";
	public static final String MSG_ACCOUNT_NOT_EXIST = "账号不存在";

	public static final String CODE_USER_MERCHANT_NULL = "4024";
	public static final String MSG_USER_MERCHANT_NULL = "商户不能为空";

	public static final String CODE_USER_NICK_NAME_NOT_NULL = "4025";
	public static final String MSG_USER_NICK_NAME_NOT_NULL = "昵称不能为空";

	public static final String CODE_NICK_NAME_EXIST = "4026";
	public static final String MSG_NICK_NAME_EXIST = "昵称已使用";

	public static final String CODE_OPENID_EXIST = "4027";
	public static final String MSG_OPENID_EXIST = "第三方账号已存在";

	public static final String CODE_OPEN_TYPE_NOT_EXIST = "4028";
	public static final String MSG_OPEN_TYPE_NOT_EXIST = "第三方账号类型错误";

	public static final String CODE_PHY_DEVICE_CHECKCODE_NOT_PASSED = "4029";
	public static final String MSG_PHY_DEVICE_CHECKCODE_NOT_PASSED = "物理设备验证码错误";

	public static final String CODE_PACKAGE_ORDER_NOT_EXIST = "4030";
	public static final String MSG_PACKAGE_ORDER_NOT_EXIST = "套餐订购记录不存在";

	public static final String CODE_ACCOUNT_UPDATE_MOBILE_REPEAT = "4031";
	public static final String MSG_ACCOUNT_UPDATE_MOBILE_REPEAT = "手机号和原来相同";

	public static final String CODE_SYN_ERROR = "4032";

	public static final String CODE_USER_MERCHANT_NOT_EXIST = "4033";
	public static final String MSG_USER_MERCHANT_NOT_EXIST = "商户不存在";

	public static final String CODE_BINDING_NOT_EXIST = "4034";
	public static final String MSG_BINDING_NOT_EXIST = "账户为空或设备类型不匹配";

	public static final String CODE_PHY_DEVICE_SN_EXIST = "4035";
	public static final String MSG_PHY_DEVICE_SN_EXIST = "设备SN已存在";

	public static final String CODE_PHY_DEVICE_IMSI_EXIST = "4036";
	public static final String MSG_PHY_DEVICE_IMSI_EXIST = "设备IMSI已存在";

	public static final String CODE_USER_DEPAND_NOT_EXIST = "4037";
	public static final String MSG_USER_DEPAND_NOT_EXIST = "没有绑定依赖模块";

	public static final String CODE_USER_DEPAND_EXIST = "4038";
	public static final String MSG_USER_DEPAND_EXIST = "没有绑定依赖模块";

	public static final String CODE_ADD_PHY_DEVICE_IMSI_ERROR = "4038";
	public static final String MSG_ADD_PHY_DEVICE_IMSI_ERROR = "IMSI须为空";

	public static final String CODE_ADD_PHY_DEVICE_NOIMSI_ERROR = "4039";
	public static final String MSG_ADD_PHY_DEVICE_NOIMSI_ERROR = "IMSI不能为空";

	public static final String CODE_ADD_PHY_DEVICE_NOMAC_ERROR = "4040";
	public static final String MSG_ADD_PHY_DEVICE_NOIMAC_ERROR = "MAC地址已经存在";

	public static final String CODE_ACCOUNT_WECHAT_EXIST = "4041";
	public static final String MSG_ACCOUNT_WECHAT_EXIST = "微信号已存在";
	public static final String CODE_ACCOUNT_WECHAT_BINDING = "4042";
	public static final String MSG_ACCOUNT_WECHAT_BINDING = "手机号已绑定了微信";
	public static final String CODE_USER_WECHAT_BINDING = "4043";
	public static final String MSG_USER_WECHAT_BINDING = "嘀嘀号已绑定了微信";

	public static final String CODE_ACCOUNT_BINDING_TYPE = "4044";
	public static final String MSG_ACCOUNT_BINDING_TYPE = "手机号未绑定该类设备";

	public static final String CODE_CLAIM_EXIST = "4045";
	public static final String MSG_CLAIM_EXIST = "驾宝认领单已存在";
	public static final String CODE_CLAIM_NOT_NULL = "4046";
	public static final String MSG_CLAIM_NOT_NULL = "驾宝认领单不能为空";

	public static final String CODE_GPS_SYNC_NOT_SUCCESS = "4047";
	public static final String MSG_GPS_SYNC_NOT_SUCCESS = "一键导航同步不成功";

	public static final String CODE_MERCHANT_IS_EXIST = "4048";
	public static final String MSG_MERCHANT_IS_EXIST = "商户名称已存在";

	public static final String CODE_USER_DD_PARAMETER_ERROR = "4049";
	public static final String MSG_USER_DD_PARAMETER_ERROR = "嘀嘀号参数为空或者错误！";

	public static final String CODE_MANCODE_PACKAGE_ERROR = "4050";
	public static final String MSG_MANCODE_PACKAGE_ERROR = "无对应设备的套餐：厂商码";
	
	public static final String CODE_MANUFCODE_EMPTY = "4051";
	public static final String MSG_MANUFCODE_EMPTY= "厂商码为空";
	
	public static final String CODE_MODELPHONE_EMPTY = "4052";
	public static final String MSG_MODELPHONE_EMPTY= "模块手机号为空";
	
	public static final String CODE_ICCID_EMPTY = "4053";
	public static final String MSG_ICCID_EMPTY= "ICCID为空";
	
	public static final String CODE_CHECKCODE_EMPTY = "4054";
	public static final String MSG_CHECKCODE_EMPTY= "CHECKCODE为空";
	
	public static final String CODE_BATCHNUM_EMPTY = "4054";
	public static final String MSG_BATCHNUM_EMPTY= "批次号为空";
	
	public static final String CODE_DEVICEID_EMPTY = "4055";
	public static final String MSG_DEVICEID_EMPTY= "设备号为空";
	
	public static final String CODE_PACKAGEINFO_EMPTY = "4057";
	public static final String MSG_PACKAGEINFO_EMPTY= "套餐信息至少有一个不为空";
	
	public static final String CODE_DEVTYPEID_EMPTY = "4058";
	public static final String MSG_DEVTYPEID_EMPTY= "设备类型为空";
	
	/**
	 * 套餐定购
	 */
	public static final String MSG_ORDER_OBJECT_NOT_NULL = "商品定购对象不能为空";
	public static final String MSG_ORDER_USERID_NOT_NULL = "用户ID不能为空";
	public static final String MSG_ORDER_TYPE_NOT_NULL = "定购的商品类型不能为空";
	public static final String MSG_ORDER_VALID_NOT_NULL = "定购月份不能为空";
	public static final String MSG_ORDER_TRAFF_NOT_NULL = "定购的流量不能为空";
	public static final String MSG_ORDER_PRODUCT_NOT_NULL = "定购的套餐id不能为空";

	/**
	 * Account Exception Constant
	 */
	public static final String CODE_USER_ATTR_IS_NULL = "6001";
	public static final String MSG_USER_ATTR_IS_NULL = "属性为空";

	public static final String CODE_USER_WECHAT_IS_NULL = "6002";
	public static final String MSG_USER_WECHAN_IS_NULL = "用户微信为空";

	public static final String CODE_REQUEST_PARAMETER = "2000";
	public static final String MSG_REQUEST_PARAMETER = "传入参数错误！";

	public static final String CODE_SERVICE_RETURN_ERROR = "1004";
	public static final String MSG_SERVICE_RETURN_ERROR = "服务返回错误！";

	public static final String CODE_SERVICE_VALIDATE_ERROR = "1005";
	public static final String MSG_SERVICE_VALIDATE_ERROR = "验证码验证失败";

	public static final String CODE_ACCESSLOGIN_ERROR = "1006";
	public static final String MSG_ACCESSLOGIN_ERROR = "用户没有绑定该类型设备";

	public static final String CODE_ONE_DEVICE_TYPE_ONE_DEVICE = "4061";
	public static final String MSG_ONE_DEVICE_TYPE_ONE_DEVICE = "一个账户下同一种设备类型不能绑定多个设备";

	public static final String CODE_USERID_PHONE_NOT_MATCH = "4062";
	public static final String MSG_USERID_PHONE_NOT_MATCH = "嘀嘀号与手机号不匹配";

	public static final String CODE_USERID_NOT_BIND_DEVICE = "4063";
	public static final String MSG_USERID_NOT_BIND_DEVICE = "嘀嘀号未绑定设备";

	public static final String CODE_USERID_NOT_MATCH_PACKAGE = "4064";
	public static final String MSG_USERID_NOT_MATCH_PACKAGE = "嘀嘀号与原有套餐不匹配";

	public static final String CODE_PHONE_NOT_EXIST = "4065";
	public static final String MSG_PHONE_NOT_EXIST = "手机号不存在";

	public static final String CODE_OLDPHONE_NOT_EQUAL_NEWMOBILE = "4066";
	public static final String MSG_OLDPHONE_NOT_EQUAL_NEWMOBILE = "新旧手机号不能相同";

	public static final String CODE_OLDPACKAGE_NOT_EQUAL_NEWPACKAGE = "4067";
	public static final String MSG_OLDPACKAGE_NOT_EQUAL_NEWPACKAGE = "新旧手机号不能相同";
	
	public static final String CODE_MERCHANT_BRAND_ALREADY_EXIST = "4068";
	public static final String MSG_MERCHANT_BRAND_ALREADY_EXIST = "商户主营品牌已存在";
	
	public static final String CODE_MERCHANT_STATUS_FORBID = "4069";
	public static final String MSG_MERCHANT_STATUS_FORBID = "商户状态为禁用";

	public static final String CODE_REMOTEBEHAVIOR_EXIST = "4070";
	public static final String MSG_REMOTEBEHAVIOR_EXIST = "此次操作已经完成";

	public static final String CODE_SAVE_REMOTEBEHAVIOR_ERROR = "4091";
	public static final String MSG_SAVE_REMOTEBEHAVIOR_ERROR = "保存数据失败";
}
