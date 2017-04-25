package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.base.EnumsID;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:08
 * <p>
 * Description: 远程操作类型
 */
public enum Operate implements EnumsID {
    OPERATE_PHOTO(1,"拍照"),
    OPERATE_VIDEO(2,"视频");

    private Integer id;
    private String text;

    private Operate(Integer id,String text){
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

    public static Operate findById(Integer id) {
        Operate[] objs = Operate.values();
        for (Operate obj : objs) {
            if (id.equals(obj.getId())) {
                return obj;
            }
        }
        return null;
    }
}