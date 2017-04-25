package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.entity.enums.MirroPosition;
import com.glsx.biz.access.common.entity.enums.Operate;
import com.glsx.biz.access.common.entity.enums.ReqSource;
import com.glsx.biz.access.service.RemoteBehaviorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class AccessServiceTest {

	@Resource
	private RemoteBehaviorService remoteBehaviorService;

	@SuppressWarnings("rawtypes")
	//@Test
	public void testBatchAddPhysicalDevice() {
		try {

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
			remb.setVideoTime(5);
			remb.setSource(ReqSource.REQ_SOURCE_WECHAT);
			remb.setMediaId("100002");

			boolean res = remoteBehaviorService.sendOperator(remb);
			System.out.println("===Remote_result===" + res);
		} catch (Exception e) {
		}
	}

}
