package com.dpt.permission.service;

import com.dpt.permission.model.SysRole;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.RoleParam;

import java.util.List;

/**
 * 角色服务服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/26 10:52
 */
public interface SysRoleService {

    /**
     * 新增方法.
     * @param param
     */
    void save(RoleParam param);

    /**
     * 更新方法.
     * @param param
     */
    void update(RoleParam param);

    /**
     * 获取所有的角色.
     * @return
     */
    List<SysRole> getAll();

    /**
     * 通过用户id获取角色列表.
     * @param userId
     * @return
     */
    List<SysRole> getRoleListByUserId(int userId);

    /**
     * 通过权限id获取角色列表.
     * @param aclId
     * @return
     */
    List<SysRole> getRoleListByAclId(int aclId);

    /**
     * 通过角色列表获取用户.
     * @param roleList
     * @return
     */
    List<SysUser> getUserListByRoleList(List<SysRole> roleList);
}
