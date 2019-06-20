package com.dpt.permission.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

/**
 * 返回类型枚举.
 *
 * @author 邓鹏涛
 * @date 2019/1/23 17:15
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    DEPT(1,"同一层级下存在相同部门");

    private Integer code;
    private String message;

}
