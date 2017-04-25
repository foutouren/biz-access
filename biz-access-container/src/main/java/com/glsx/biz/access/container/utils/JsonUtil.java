package com.glsx.biz.access.container.utils;

import com.glsx.biz.access.common.entity.Media;
import com.glsx.biz.access.common.vo.NotifyMsg;
import com.glsx.cloudframework.core.util.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonUtil {
	private static ObjectMapper mapper = new ObjectMapper();

	
	/**
	 * 美化JSON
	 * @param jsonStr
	 * @return
	 */
	public static String pretty(String jsonStr) {
		try {
			Object json = mapper.readValue(jsonStr, Object.class);
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonStr;
	}

	/**
	 * source中包含多少个param
	 * @param source
	 * @param param
	 * @return
	 */
	public static int containsParamCount(String source, String param) {
		int cnt = 0;
		int offset = 0;
		while((offset = source.indexOf(param, offset)) != -1){
			offset = offset + param.length();
			cnt++;
		}
		return cnt;
	}

	public static List<Media> parseMediaByDocument(String data) {
		List<Media> res = new ArrayList<Media>();
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(data);
			Element rootEle = doc.getRootElement();
			List<Element> elemetList = rootEle.elements("mediafile");
			Integer mediaType = null;
			for (Element eleItem : elemetList) {
				Integer cameraNum = null;
				String fileexName = "jpg";

				String mediaTypeValue = eleItem.elementText("mediatype");
				String cameranoValue = eleItem.elementText("camerano");
				String fileexNameValue = eleItem.elementText("fileextname");

				if (!StringUtils.isEmpty(mediaTypeValue)) {
					mediaType = Integer.parseInt(mediaTypeValue);
				}
				if (!StringUtils.isEmpty(cameranoValue)) {
					cameraNum = Integer.parseInt(cameranoValue);
				}
				if (!StringUtils.isEmpty(fileexNameValue)) {
					fileexName = fileexNameValue;
				}

				String mediaUrl = eleItem.elementText("mediaurl");
				String smallMediaUrl = eleItem.elementText("smallmediaurl");
				if (!StringUtils.isEmpty(mediaUrl)) {
					mediaUrl = mediaUrl + "." + fileexName;
				}
				if (!StringUtils.isEmpty(smallMediaUrl)) {
					smallMediaUrl = smallMediaUrl + "." + fileexName;
				}

				Media media = new Media();
				media.setMediaType(mediaType);
				media.setMirroType(cameraNum);
				media.setMediaUrl(mediaUrl);
				media.setSmallUrl(smallMediaUrl);
				res.add(media);
			}
		} catch (Exception doce) {}

		return res;
	}
}
