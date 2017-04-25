package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.entity.RemoteBehavior;
import com.glsx.biz.access.common.entity.enums.MirroPosition;
import com.glsx.biz.access.common.entity.enums.Operate;
import com.glsx.biz.access.common.entity.enums.ReqSource;
import com.glsx.biz.access.common.vo.MediaSearch;
import com.glsx.biz.access.service.MediaService;
import com.glsx.biz.access.service.RemoteBehaviorService;
import com.glsx.cloudframework.core.datastructure.page.Pagination;
import com.glsx.cloudframework.core.exception.ServiceException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/biz-*.xml" })
public class MediaServiceTest {

	@Resource
	private MediaService mediaService;

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
			List<Media> res = mediaService.getLastImageByUserId(51001368);
			System.out.println("===Remote_result===" + res.toString());
		} catch (Exception e) {
		}
	}

	@Test
	public void testDeleteMedia() {
		try {
			mediaService.deleteMedia(1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSearchMedias() {
		try {
			MediaSearch mediaSearch = new MediaSearch();
			mediaSearch.setUserId(51001368);
			mediaSearch.setMediaType(1);
			mediaSearch.setReportReason(2);

			Pagination page = new Pagination();
			page.setPageNo(2);
			page.setPageSize(2);

			page = mediaService.getMediaes(mediaSearch,page);
			System.out.println("===" + JSONArray.fromObject(page.getList()));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
