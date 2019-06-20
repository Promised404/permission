package com.dpt.permission.service.impl;

import com.dpt.permission.beans.CacheKeyConstants;
import com.dpt.permission.common.RequestHolder;
import com.dpt.permission.dao.SysAclMapper;
import com.dpt.permission.dao.SysRoleAclMapper;
import com.dpt.permission.dao.SysRoleUserMapper;
import com.dpt.permission.enums.SysAclEnum;
import com.dpt.permission.model.SysAcl;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.service.SysCacheService;
import com.dpt.permission.service.SysCoreService;
import com.dpt.permission.util.JsonMapper;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 核心服务实现.
 *
 * @author 邓鹏涛
 * @date 2019/1/27 20:26
 */
@Service
public class SysCoreServiceImpl implements SysCoreService {

    @Autowired
    private SysAclMapper sysAclMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;
    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;
    @Autowired
    private SysCacheService sysCacheService;


    @Override
    public List<SysAcl> getCurrentUserAclList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    @Override
    public List<SysAcl> getRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

    /**
     * 查询用户权限点列表
     * @param userId
     * @return
     */
    @Override
    public List<SysAcl> getUserAclList(int userId) {
        if (isSuperAdmin()) {
            return sysAclMapper.getAll();
        }
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    @Override
    public boolean hasUrlAcl(String url) {
        if (isSuperAdmin()) {
            //return true;
        }
        List<SysAcl> aclList = sysAclMapper.getByUrl(url);
        if (CollectionUtils.isEmpty(aclList)) {
            return true;
        }
        List<SysAcl> userAclIdList = getCurrentUserAclListFromCache();
        Set<Integer> userAclIdSet = userAclIdList.stream()
                .map(acl -> acl.getId())
                .collect(Collectors.toSet());
        // 规则：只要有一个权限点有权限，那么我们就认为有访问权限
        boolean hasValid = false;
        for (SysAcl acl : aclList) {
            // 判断一个用户是否具有某个权限点的访问权限
            if (acl == null || acl.getStatus() != SysAclEnum.STATUS_NORMAL.getCode()) { // 权限点无效
                continue;
            }
            hasValid = true;
            if (userAclIdSet.contains(acl.getId())) {
                return true;
            }
        }
        if (!hasValid) {
            return true;
        }
        return false;
    }

    /**
     * 将当前用户权限做缓存.
     * @return
     */
    public List<SysAcl> getCurrentUserAclListFromCache() {
        int userId = RequestHolder.getCurrentUser().getId();
        String cacheValue = sysCacheService.getFromCache(CacheKeyConstants.USER_ACLS, String.valueOf(userId));
        if (StringUtils.isBlank(cacheValue)) {
            List<SysAcl> aclList = getCurrentUserAclList();
            if (CollectionUtils.isNotEmpty(aclList)) {
                sysCacheService.saveCache(JsonMapper.object2String(aclList), 600, CacheKeyConstants.USER_ACLS, String.valueOf(userId));
            }
            return aclList;
        }
        return JsonMapper.string2Obj(cacheValue, new TypeReference<List<SysAcl>>() {
        });
    }

    /**
     * 判断是否是超级管理员.
     * @return
     */
    public boolean isSuperAdmin() {
        //TODO:这里是自己定义的一个假的超级管理员规则，实际中要根据项目进行修改，
        //可以是配置文件获取，也可以是指定某个用户，也可以指定某个角色
        SysUser sysUser = RequestHolder.getCurrentUser();
        if (sysUser.getMail().contains("admin")) {
            return true;
        }
        return false;
    }

}
