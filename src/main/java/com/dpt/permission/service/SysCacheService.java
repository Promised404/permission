package com.dpt.permission.service;

import com.dpt.permission.beans.CacheKeyConstants;

/**
 * 系统缓存服务接口.
 *
 * @author 邓鹏涛
 * @date 2019/1/30 21:37
 */
public interface SysCacheService {

    void saveCache(String toSaveValue, int timeoutSeconds, CacheKeyConstants prefix);

    void saveCache(String toSaveValue, int timeoutSeconds, CacheKeyConstants prefix, String... keys);

    String getFromCache(CacheKeyConstants prefix, String... keys);

}
