package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:08
 * <p>
 * Description: 用户请求来源
 */
public enum ReqSource implements EnumsID {
    REQ_SOURCE_WECHAT(1,"微信"),
    REQ_SOURCE_DIDIHUAPP(2,"驾宝app"),
    REQ_SOURCE_ATTACK(3,"碰撞上报");

    private Integer id;
    private String text;

    private ReqSource(Integer id, String text){
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

    public static ReqSource findById(Integer id) {
        ReqSource[] objs = ReqSource.values();
        for (ReqSource obj : objs) {
            if (id.equals(obj.getId())) {
                return obj;
            }
        }
        return null;
    }
}