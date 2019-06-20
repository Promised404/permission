package com.dpt.permission.service;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.beans.PageResult;
import com.dpt.permission.model.*;
import com.dpt.permission.param.SearchLogParam;

import java.util.List;

/**
 * 日志服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/31 12:30
 */
public interface SysLogService {

    /**
     * 保存部门日志.
     * @param before
     * @param after
     */
    void saveDeptLog(SysDept before, SysDept after);

    /**
     * 保存用户日志.
     * @param before
     * @param after
     */
    void saveUserLog(SysUser before, SysUser after);

    /**
     * 保存权限模块日志.
     * @param before
     * @param after
     */
    void saveAclModuleLog(SysAclModule before, SysAclModule after);

    /**
     * 保存权限点日志.
     * @param before
     * @param after
     */
    void saveAclLog(SysAcl before, SysAcl after);

    /**
     * 保存角色日志.
     * @param before
     * @param after
     */
    void saveRoleLog(SysRole before, SysRole after);

    /**
     * 保存角色权限点日志.
     * @param roleId
     * @param before
     * @param after
     */
    void saveRoleAclLog(int roleId, List<Integer> before, List<Integer> after);

    /**
     * 保存角色用户日志日志.
     * @param roleId
     * @param before
     * @param after
     */
    void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after);

    /**
     * 查询日志列表.
     * @param param
     * @param page
     * @return
     */
    PageResult<SysLogWithBLOBs> searchPageList(SearchLogParam param, PageQuery page);

    /**
     *恢复修改.
     * @param id
     */
    void recover(int id);

}
