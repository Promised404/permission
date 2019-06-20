package com.dpt.permission.service;

import com.dpt.permission.model.SysUser;

import java.util.List;

/**
 * 角色用户关系服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/29 13:38
 */
public interface SysRoleUserService {

    /**
     * 通过角色id查找所有用户.
     * @param role
     * @return
     */
    List<SysUser> getListByRoleId(int role);

    /**
     * 改变角色用户关系.
     * @param roleId
     * @param userIdList
     */
    void changeRoleUsers(int roleId, List<Integer> userIdList);

}
