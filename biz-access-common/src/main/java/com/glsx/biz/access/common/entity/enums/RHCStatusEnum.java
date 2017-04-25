package com.glsx.biz.access.common.entity.enums;

import com.glsx.biz.access.common.entity.enums.usertype.IntegerEnumUserType;

/**
 * Created with IntelliJ IDEA.
 * User: Lenovo
 * Date: 2016/12/19
 * Time: 16:52
 * <p>
 * Description: 用户发送请求状态 - 枚举
 */
public class RHCStatusEnum extends IntegerEnumUserType<RHCStatus> {
    public RHCStatusEnum() {
        super(RHCStatus.class);
    }
}