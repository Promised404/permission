package com.dpt.permission.service;

import com.dpt.permission.param.AclModuleParam;

/**
 * 权限模块服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/25 13:33
 */
public interface SysAclModuleService {

    /**
     * 新增权限模块.
     * @param param
     */
    void save(AclModuleParam param);

    /**
     * 更新权限模块.
     * @param param
     */
    void update(AclModuleParam param);

    /**
     * 删除权限模块.
     * @param id
     */
    void delete(int id);
}
