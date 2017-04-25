package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.MirroPosition;
import com.glsx.biz.access.common.entity.enums.usertype.IntegerEnumUserType;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:52
 * <p>
 * Description: 用户请求来源 - 枚举
 */
public class ReqSourceEnum extends IntegerEnumUserType<ReqSource> {
    public ReqSourceEnum() {
        super(ReqSource.class);
    }
}