package com.dpt.permission.service;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.beans.PageResult;
import com.dpt.permission.model.SysAcl;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.AclParam;

/**
 * 权限点服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/25 21:40
 */
public interface SysAclService {

    /**
     * 新增用户.
     *
     * @param param
     */
    void save(AclParam param);

    /**
     * 更新用户.
     *
     * @param param
     */
    void update(AclParam param);

    /**
     * 分页查询用户.
     *
     * @param aclModuleId
     * @param pageQuery
     * @return
     */
    PageResult<SysAcl> getPageByAclId(int aclModuleId, PageQuery pageQuery);
}
