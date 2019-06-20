package com.dpt.permission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 邓鹏涛
 * @date 2019/1/27 22:51
 */
@Getter
@AllArgsConstructor
public enum  SysAclEnum {

    /**
     * 状态正常
     */
    STATUS_NORMAL(1,"正常"),
    /**
     * 状态冻结
     */
    STATUS_FROZEN(2,"冻结");

    private int code;
    private String message;

}
