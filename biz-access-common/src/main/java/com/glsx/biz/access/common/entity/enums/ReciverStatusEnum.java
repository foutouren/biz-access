package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.usertype.IntegerEnumUserType;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:52
 * <p>
 * Description: 数据接收状态 - 枚举
 */
public class ReciverStatusEnum extends IntegerEnumUserType<ReciverStatus> {
    public ReciverStatusEnum() {
        super(ReciverStatus.class);
    }
}