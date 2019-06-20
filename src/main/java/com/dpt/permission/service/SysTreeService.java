package com.dpt.permission.service;

import com.dpt.permission.dto.AclModuleLevelDto;
import com.dpt.permission.dto.DeptLevelDto;

import java.util.List;

/**
 * 树服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/21 21:09
 */
public interface SysTreeService {

    /**
     *获取部门树.
     * @return
     */
    List<DeptLevelDto> deptTree();

    /**
     * 获取权限模块树.
     * @return
     */
    List<AclModuleLevelDto> aclModuleTree();

    /**
     *获取角色树.
     * @param roleId
     * @return
     */
    List<AclModuleLevelDto> roleTree(int roleId);

    /**
     * 获取用户权限树.
     * @param userId
     * @return
     */
    List<AclModuleLevelDto> userAclTree(int userId);

}
