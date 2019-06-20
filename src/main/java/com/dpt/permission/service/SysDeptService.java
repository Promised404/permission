package com.dpt.permission.service;

import com.dpt.permission.param.DeptParam;

/**
 * 部门服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/21 20:03
 */
public interface SysDeptService {

    /**
     * 新增部门.
     * @param param
     */
    void save(DeptParam param);

    /**
     * 更新部门.
     * @param param
     */
    void update(DeptParam param);

    /**
     * 删除部门
     * @param id
     */
    void delete(int id);

}
