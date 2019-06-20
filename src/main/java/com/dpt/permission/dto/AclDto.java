package com.dpt.permission.dto;

import com.dpt.permission.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 *权限点Dto.
 *
 * @author 邓鹏涛
 * @date 2019/1/27 20:14
 */
@Getter
@Setter
@ToString
public class AclDto extends SysAcl {

    //是否默认选中
    private boolean checked = false;

    //是否有权限操作
    private boolean hasAcl = false;

    public static AclDto adapt(SysAcl acl) {
        AclDto dto = new AclDto();
        BeanUtils.copyProperties(acl, dto);
        return dto;
    }

}
