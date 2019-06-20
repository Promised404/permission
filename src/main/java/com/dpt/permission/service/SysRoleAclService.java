package com.dpt.permission.service;

import java.util.List;

/**
 * 角色权限关系服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/29 13:38
 */
public interface SysRoleAclService {

    /**
     * 改变角色权限关系.
     * @param roleId
     * @param aclIdList
     */
    void changeRoleAcls(Integer roleId, List<Integer> aclIdList);

}
