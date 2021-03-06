package com.dpt.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 权限模块参数.
 *
 * @author 邓鹏涛
 * @date 2019/1/25 12:21
 */
@Getter
@Setter
@ToString
public class AclModuleParam {

    private Integer id;

    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 64, message = "权限名称长度需要在2-64个之间")
    private String name;

    private Integer parentId = 0;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @NotNull(message = "权限模块状态不能为空")
    @Max(value = 1, message = "权限模块状态不合法")
    @Min(value = 0, message = "权限模块状态不合法")
    private Integer status;

    @Length(max = 200, message = "权限模块的备注需要在200个字之间")
    private String remark;


}
