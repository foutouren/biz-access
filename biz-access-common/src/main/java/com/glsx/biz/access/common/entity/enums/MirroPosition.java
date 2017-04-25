package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:08
 * <p>
 * Description: 后视镜类型
 */
public enum MirroPosition implements EnumsID {
    MIRRO_POSITION_DEFAULT(0,"无"),
    MIRRO_POSITION_FRONT(1,"前置后视镜"),
    MIRRO_POSITION_BEHIND(2,"后置后视镜"),
    MIRRO_POSITION_DOUBLE(3,"前后后视镜");

    private Integer id;
    private String text;

    private MirroPosition(Integer id, String text){
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

    public static MirroPosition findById(Integer id) {
        MirroPosition[] objs = MirroPosition.values();
        for (MirroPosition obj : objs) {
            if (id.equals(obj.getId())) {
                return obj;
            }
        }
        return null;
    }
}