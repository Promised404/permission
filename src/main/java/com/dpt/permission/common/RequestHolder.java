package com.dpt.permission.common;

import com.dpt.permission.model.SysUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 本地线程工具.
 *
 * @author 邓鹏涛
 * @date 2019/1/24 20:31
 */
public class RequestHolder {

    private static final ThreadLocal<SysUser> userHolder = new ThreadLocal<SysUser>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    /**
     * 本地线程设置登录用户.
     * @param sysUser
     */
    public static void add(SysUser sysUser) {
        userHolder.set(sysUser);
    }

    /**
     * 本地线程设置request.
     * @param response
     */
    public static void add(HttpServletRequest response) {
        requestHolder.set(response);
    }

    /**
     * 获取本地线程的登录用户.
     * @return
     */
    public static SysUser getCurrentUser() {
        return userHolder.get();
    }

    /**
     * 获取本地线程的request.
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    /**
     *删除当前线程的本地值(SysUser、request)
     */
    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}
