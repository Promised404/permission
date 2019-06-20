package com.dpt.permission.filter;

import com.dpt.permission.common.RequestHolder;
import com.dpt.permission.model.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录过滤器.
 *
 * @author 邓鹏涛
 * @date 2019/1/24 20:48
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        SysUser user = (SysUser)req.getSession().getAttribute("user");
        if (user == null) {
            String path = "/signin.jsp";
            resp.sendRedirect(path);
            return;
        }
        RequestHolder.add(user);
        RequestHolder.add(req);
        filterChain.doFilter(req, resp);
        return;

    }

    @Override
    public void destroy() {

    }
}
