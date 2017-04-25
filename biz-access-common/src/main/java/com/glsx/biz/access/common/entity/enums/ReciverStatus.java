package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:08
 * <p>
 * Description: 数据接收状态
 */
public enum ReciverStatus implements EnumsID {
    MEDIA_UN_RECIVER(1,"未接收"),
    MEDIA_RECIVERED(2,"已接收");

    private Integer id;
    private String text;

    private ReciverStatus(Integer id, String text){
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

    public static ReciverStatus findById(Integer id) {
        ReciverStatus[] objs = ReciverStatus.values();
        for (ReciverStatus obj : objs) {
            if (id.equals(obj.getId())) {
                return obj;
            }
        }
        return null;
    }
}