package com.dpt.permission.service;

import com.dpt.permission.model.SysAcl;

import java.util.List;

/**
 * 系统核心服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/27 20:23
 */
public interface SysCoreService {

    /**
     * 获取当前用户权限列表
     * @return
     */
    List<SysAcl> getCurrentUserAclList();

    /**
     * 获取角色的权限点列表
     * @param roleId
     * @return
     */
    List<SysAcl> getRoleAclList(int roleId);

    /**
     * 获取用户权限列表
     * @param userId
     * @return
     */
    List<SysAcl> getUserAclList(int userId);

    /**
     * 是否有这个url权限.
     * @param url
     * @return
     */
    boolean hasUrlAcl(String url);
}
