package com.glsx.biz.access.container.service.impl;

import com.glsx.biz.access.container.utils.ApacheHttpUtils;
import com.glsx.cloudframework.core.util.StringUtil;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2017/3/8
 * Time: 11:38
 * <p>
 * Description:
 */
public class TestAddress {

    public static  void main(String[] args) {
        JSONObject result = ApacheHttpUtils.gps2Address("22.54779", "113.94454333333333");// lat long
        if (null != result) {
            String activeAddress = result.getString("formatted_address");
            System.out.println("===" + result.toString());
        }
    }

}