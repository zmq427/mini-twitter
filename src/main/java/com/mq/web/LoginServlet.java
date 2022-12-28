package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.mq.pojo.R;
import com.mq.pojo.User;
import com.mq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用service查询
        User user = service.login(username, password);
        System.out.println(user);

        //获取对应的字符输出流并设置content type
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        //判断是否查询到
        if (user != null) {
            R<User> r = R.success(user);
            writer.write(JSON.toJSONString(r));
            System.out.println(JSON.toJSONString(r));
            // 获取session对象
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("login successfully");
        } else {
            // 未查询到
            R<User> r = R.error("login failed, no matching userinfo...");
            writer.write(JSON.toJSONString(r));
            System.out.println("login failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
