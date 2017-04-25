package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:08
 * <p>
 * Description: 用户发送请求状态
 */
public enum RHCStatus implements EnumsID {
    RHC_STATUS_DEFAULT(0,"未发送"),
    RHC_STATUS_SENDED(1,"已发送"),
    RHC_STATUS_SUCCESS(2,"拍照完成"),
    REQ_SOURCE_SEND_FAIL(3,"发送失败");

    private Integer id;
    private String text;

    private RHCStatus(Integer id, String text){
        this.id = id;
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public static RHCStatus findById(Integer id) {
        RHCStatus[] objs = RHCStatus.values();
        for (RHCStatus obj : objs) {
            if (id.equals(obj.getId())) {
                return obj;
            }
        }
        return null;
    }
}