package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.entity.enums.MirroPosition;
import com.glsx.biz.access.common.entity.enums.Operate;
import com.glsx.biz.access.common.entity.enums.ReqSource;
import com.glsx.biz.access.common.vo.SyncMedia;
import com.glsx.biz.access.service.RemoteBehaviorService;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class RemoteServiceTest {

	@Resource
	private RemoteBehaviorService remoteBehaviorService;

	@SuppressWarnings("rawtypes")
	@Test
	public void testGetRemoteByUserIdMediaId() {
		try {
			RemoteBehavior remoteBehavior = remoteBehaviorService.getRemoteBehaviorByUserId(51001368, "100001");
			System.out.println("===Remote_Behavior===" + remoteBehavior.toString());
		} catch (Exception e) {
		}
	}

	@Test
	public void testSendRemoteBehavior() {
		try {
			RemoteBehavior remb = new RemoteBehavior();
			remb.setUserId(51001368);
			remb.setOperateType(Operate.OPERATE_PHOTO);
			remb.setOperateValue(MirroPosition.MIRRO_POSITION_FRONT);
			//remb.setVideoTime(5);
			remb.setSource(ReqSource.REQ_SOURCE_WECHAT);

			boolean res = remoteBehaviorService.sendOperator(remb);
			System.out.println("===Remote_result===" + res);
		} catch (Exception e) {
		}
	}

	@Test
	public void testRemoteBehaviorLast() {
		try {
			RemoteBehavior res = remoteBehaviorService.getRemoteBehaviorLastByIMEI("861661608020121");
			System.out.println("===Remote_result===" + JSONObject.fromObject(res));
		} catch (Exception e) {
		}
	}

	@Test
	public void syncMedia() {
		try {
			Integer userId = null;
			Integer source = null;
			String imei = "861661608020121";
			String mediaId = "";
			String data = "<xml><mediafile><camerano>1</camerano><fileextname>jpg</fileextname><mediatype>1</mediatype><mediaurl>T1qRdTBCbv1R4bAZ6B</mediaurl><smallmediaurl>T1qtdTByLv1R4bAZ6B</smallmediaurl></mediafile></xml>";
			String gpsLong = "12.0002";
			String gpsLat = "34.1235";
			Integer reportReason = 3;
			String mediaDateValue = "2017-03-01 12:48:21";
			SyncMedia syncMedia = new SyncMedia(userId, imei, source, mediaId, data, gpsLong, gpsLat, reportReason, mediaDateValue);

			remoteBehaviorService.saveSyncMeida(syncMedia);

		} catch (Exception e) {
		}
	}

	//51001368
	@Test
	public void getConsummation() {
		try {
			Integer userId = 51001368;
			RemoteBehavior res = remoteBehaviorService.getRemoteBehaviorConsummate(userId);
			System.out.println("===Remote===" + JSONObject.fromObject(res));
		} catch (Exception e) {
		}
	}
}
