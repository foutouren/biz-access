package com.glsx.biz.access.container.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

import com.glsx.cloudframework.core.util.Md5Encrypt;
import com.glsx.cloudframework.core.util.StringUtil;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 	http调用工具类
 *  @author wangxw1
 *  @date 2016-12-19 13:41
 */
public class ApacheHttpUtils {

	private static Log log = LogFactory.getLog(ApacheHttpUtils.class);

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

	private static final String URL_PARAM_CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
	}

	/**
	 * POST方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLPost(String url, Map<String, String> params, String enc) {

		String response = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/json;charset=" + enc);
			// 将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for (String key : keySet) {
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}

		return response;
	}

	/**
	 * GET方式提交数据
	 * 
	 * @param url
	 *            待请求的URL
	 * @param params
	 *            要提交的数据
	 * @param enc
	 *            编码
	 * @return 响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLGet(String url, Map<String, String> params, String enc) {

		String response = EMPTY;
		GetMethod getMethod = null;
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);

		if (strtTotalURL.indexOf("?") == -1) {
			strtTotalURL.append(url).append("?").append(getUrl(params, enc));
		} else {
			strtTotalURL.append(url).append("&").append(getUrl(params, enc));
		}
		log.debug("GET请求URL = \n" + strtTotalURL.toString());

		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			// 执行getMethod
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = getMethod.getResponseBodyAsString();
			} else {
				log.debug("响应状态码 = " + getMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
				getMethod = null;
			}
		}

		return response;
	}

	/**
	 * 据Map生成URL字符串
	 * 
	 * @param map
	 *            Map
	 * @param valueEnc
	 *            URL编码
	 * @return URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {

		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}

	public static String URLPostJson(String url, JSONObject params, String string) {

		String response = EMPTY;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
            String transJson = params.toString();
            RequestEntity se = new StringRequestEntity(transJson, "application/json", "UTF-8");
            postMethod.setRequestEntity(se);
//            postMethod.setRequestBody(transJson);
            
            //使用系统提供的默认的恢复策略
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            //设置超时的时间
            postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 10000);
			// 执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			} else {
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		} catch (HttpException e) {
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("发生网络异常", e);
			e.printStackTrace();
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
				postMethod = null;
			}
		}

		return response;
	}

	public static JSONObject gps2Address(String lat, String lon) {
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtil.isEmpty(lat) && !StringUtil.isEmpty(lon)) {
			// 百度的key
			params.put("ak", "rkWGzgudjarAgCU94FO6bebz");
			// 位置，纬度，
			params.put("location", lat + "," + lon);
			String url = "http://api.map.baidu.com/geocoder/v2/?output=json&coordtype=gcj02ll";
			try {
				String result = httpPost(url, params);
				log.info("pgs info:" + result);
				JSONObject value = JSONObject.fromObject(result);
				if (value.getInt("status") == 0) {
					JSONObject addressObject = value.getJSONObject("result");
					return addressObject;
				} else {
					log.warn("baidu interface or params was invalid!");
				}

			} catch (Exception e) {
				log.warn("mapservice catched an exception:", e);
			}
		} else {
			log.warn("no lat or lng of gps!");
		}
		return null;
	}

	public static String httpPost(String uri, Map<String, String> params) throws Exception {
		StringBuilder sb = new StringBuilder();
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			nvps.add(new BasicNameValuePair(key, params.get(key)));
			sb.append(key).append(params.get(key));
		}

		String sign = sb.toString();
		sign = Md5Encrypt.md5(sb.toString()).toUpperCase();
		nvps.add(new BasicNameValuePair("sign", sign));

		return basePrint(uri, nvps);
	}

	public static String basePrint(String uri, List<NameValuePair> nvps)
			throws Exception {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(uri);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Charset.forName("UTF-8")));
		HttpResponse response = httpclient.execute(httpPost);
		try {
			HttpEntity entity = response.getEntity();
			String json = EntityUtils.toString(entity, Charset.forName("UTF-8"));
			return json;
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}
