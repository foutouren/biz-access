package com.glsx.biz.access.container.queue;

import com.glsx.biz.access.common.vo.SyncMedia;
import com.glsx.biz.access.service.RemoteBehaviorService;
import com.glsx.cloudframework.core.util.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.annotation.Resource;

/**
 * 远程拍照多媒体内容上报
 * @author Lenovo
 *
 */
public class SyncMediaReceiver extends MessageReceiver {

	private static final Logger logger = Logger.getLogger(SyncMediaReceiver.class.getName());

	@Resource
	private RemoteBehaviorService remoteBehaviorService;

	public void processMessage(String topic, byte[] message) {
		String reciveParam = new String(message);
		logger.warn("远程拍照多媒体内容消费：" + reciveParam);
		
		// 具体处理业务逻辑
		if(!StringUtils.isNullOrEmpty(reciveParam)){
			SyncMedia syncMedia = (SyncMedia) JSONObject.toBean(JSONObject.fromObject(reciveParam), SyncMedia.class);
			remoteBehaviorService.saveSyncMeida(syncMedia);
		}
	}

}
