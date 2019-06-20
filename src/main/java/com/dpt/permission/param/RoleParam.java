package com.dpt.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 角色参数.
 *
 * @author 邓鹏涛
 * @date 2019/1/26 10:59
 */
@Getter
@Setter
@ToString
public class RoleParam {

    private Integer id;

    @NotBlank(message = "角色名称不能为空")
    @Length(min = 2, max = 64, message = "角色名称长度需要在2-20个字符之间")
    private String name;

    @NotNull(message = "必须指定角色的类型")
    @Min(value = 1, message = "角色不合法")
    @Max(value = 2, message = "角色不合法")
    private Integer type = 1;

    @NotNull(message = "必须指定角色的状态")
    @Min(value = 0, message = "角色状态不合法")
    @Max(value = 1, message = "角色状态不合法")
    private Integer status = 1;

    @Length(min = 0, max = 200, message = "角色备注长度需要少于200个字")
    private String remark;


}
