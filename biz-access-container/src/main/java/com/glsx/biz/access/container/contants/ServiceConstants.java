package com.glsx.biz.access.container.contants;

import com.glsx.cloudframework.core.context.config.PropertyConfigurer;
import com.glsx.cloudframework.core.util.PropertiesConfig;

public class ServiceConstants {
	
	/**
	 * 删除状态标识
	 */
	public  final static Short COMMON_DELETE_FLAG = -9;
	
	/**
	 * 新建状态标识
	 */
	public  final static Short COMMON_NEW_FLAG = 1;
	
	/**
	 * 物理设备已激活状态标识
	 */
	public  final static Short DEVICE_ACTIVED_FLAG = 2;
	
	/**
	 * 物理设备已拆出状态标识
	 */
	public  final static Short DEVICE_OUT_FLAG = 3;
	
	/**
	 * 物理设备已激活状态标识
	 */
	public  final static Short DEVICE_REPORT_FLAG = 4;
	
	/**
	 * 已解绑
	 */
	public  final static Short DEVICE_UNBIND_FLAG = 5;
	
	/**
	 * 嘀嘀号已启用标识
	 */
	public  final static Short USER_USED_FLAG = 2;
	
	/**
	 * 主绑嘀嘀号标识
	 */
	public  final static Short BINDING_MAIN_FLAG = 1;
	
	/**
	 * 同步接入平台成功
	 */
	public  final static Short SYNC_LOG_SUCCESS_FLAG = 1;
	
	/**
	 * 同步接入平台失败
	 */
	public  final static Short SYNC_LOG_FAIL_FLAG = 2;
	
	/**
	 * 套餐使用状态
	 */
	public  final static Short PACKAGE_ON_LINE = 2;

	/** 下发指令 - URL地址 */
	public final static String SEND_COMMOND_URL = PropertyConfigurer.getString("send.media.commond.url");
	/** 获取设备状态 - URL地址 */
	public final static String SEND_DEVICESTATUS_URL = PropertyConfigurer.getString("send.device.status.url");
	/** 下发指令 - APPID */
	public final static String SEND_COMMON_APPID = PropertyConfigurer.getString("send.media.commond.appid");
	/** 下发指令 - APP_SECRET */
	public final static String SEND_COMMON_APPSECRET = PropertyConfigurer.getString("send.media.commond.appsecret");

	/** 拍照操作 */
	public final static String MEDIA_OPERATE_PHOTO = "takePhoto";
	/** 视频操作 */
	public final static String MEDIA_OPERATE_VIDEO = "takeVideo";

	public final static String SYNC_MEDIA_OBJECT = "SYNC_MEDIA";
}
