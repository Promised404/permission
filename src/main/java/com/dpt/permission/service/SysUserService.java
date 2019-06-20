package com.dpt.permission.service;

import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.beans.PageResult;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.UserParam;

import java.util.List;

/**
 * 用户服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/23 12:37
 */
public interface SysUserService {

    /**
     * 新增用户.
     *
     * @param param
     */
    void save(UserParam param);

    /**
     * 更新用户.
     *
     * @param param
     */
    void update(UserParam param);

    /**
     * 根据关键字查询用户(邮箱/手机号码).
     *
     * @param keyword
     * @return
     */
    SysUser findByKeyword(String keyword);

    /**
     * 分页查询用户.
     *
     * @param deptId
     * @param pageQuery
     * @return
     */
    PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery);

    /**
     * 获取所有用户.
     *
     * @return
     */
    List<SysUser> getAll();
}
