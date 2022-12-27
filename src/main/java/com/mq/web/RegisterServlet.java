package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.mq.mapper.UserMapper;
import com.mq.pojo.R;
import com.mq.pojo.User;
import com.mq.service.UserService;
import com.mq.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        boolean isSuccess = service.register(username, password);

        if (!isSuccess) {
            resp.setContentType("text/html;charset=utf-8");
            R r = R.error("Register failed, username already exists...");
            resp.getWriter().write(JSON.toJSONString(r));
        } else {
            R r = R.success("Register successfully");
            resp.getWriter().write(JSON.toJSONString(r));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
