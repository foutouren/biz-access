package com.glsx.biz.access.container.queue;

import com.glsx.biz.access.common.vo.NotifyMsg;
import com.glsx.cloudframework.core.context.config.PropertyConfigurer;
import com.glsx.cloudframework.core.util.HttpUtils;
import com.glsx.cloudframework.core.util.StringUtils;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 * 远程拍照消息推送消费
 * @author Lenovo
 *
 */
public class NotifyMediaReceiver extends MessageReceiver {

	private static final Logger logger = Logger.getLogger(NotifyMediaReceiver.class.getName());

	public void processMessage(String topic, byte[] message) {
		String reciveParam = new String(message);
		logger.warn("远程拍照消息推送消费：" + reciveParam);
		
		// 具体处理业务逻辑
		if(!StringUtils.isNullOrEmpty(reciveParam)){
			//http://192.168.3.161:9994/didi-cb-admin/remotePicture/notice.shtml?appkey=d67f9a20abd24900&userId={0}&type={1}&mediaId={2}&source={3}&event={4}
			NotifyMsg notifyMsg = (NotifyMsg) JSONObject.toBean(JSONObject.fromObject(reciveParam), NotifyMsg.class);
			String sendMsgUrl = PropertyConfigurer.getString("remote.media.send.msg");
			String msgContent = MessageFormat.format(sendMsgUrl, String.valueOf(notifyMsg.getUserId()), notifyMsg.getMediaType(), notifyMsg.getMediaId(), notifyMsg.getSourceId(), notifyMsg.getReportReason());
			logger.warn("===SendToClientMSG===" + msgContent);
			String httpRes = HttpUtils.get(msgContent);
			logger.warn("===SendToClientMSG_res===" + httpRes);
		}
	}

}
