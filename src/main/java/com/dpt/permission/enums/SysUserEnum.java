package com.dpt.permission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 邓鹏涛
 * @date 2019/1/29 19:13
 */
@Getter
@AllArgsConstructor
public enum SysUserEnum {

    /**
     * 状态正常
     */
    STATUS_NORMAL(1,"正常"),
    /**
     * 状态冻结
     */
    STATUS_FROZEN(0,"冻结"),
    /**
     * 状态删除
     */
    STATUS_DELETE(2,"删除");


    private int code;
    private String message;

}
