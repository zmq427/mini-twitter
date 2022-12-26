package com.mq.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 判断访问资源路径是否和登录注册相关
        String[] urls = {"/login.jsp"};
        // 获取当前访问的资源路径
        String url = request.getRequestURL().toString();
        // 循环判断
        for (String u: urls) {
            if (url.contains(u)) {
                // 找到了
                // 放行
                filterChain.doFilter(request, servletResponse);
                return;
            }
        }

        // 判断session中是否有user
        HttpSession session = request.getSession();
        Object user = session.getAttribute("username");

        if (user != null) {
            //登录过了，放行
            filterChain.doFilter(request, servletResponse);
        } else {
            //没有登录，存储提示信息，跳转到登录页面
            request.setAttribute("login_msg", "You haven't logged in...");
            request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
