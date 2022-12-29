package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.mq.pojo.R;
import com.mq.pojo.User;
import com.mq.pojo.UserWithStatus;
import com.mq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getUsersServlet")
public class GetUsersServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer followerId = Integer.parseInt(req.getParameter("followerId"));
        List<UserWithStatus> users = service.getUsers(followerId);

        R r;
        if (users != null) {
            r = R.success(users);
        } else {
            r = R.error("Getting user list failed");
        }
        String jsonStr = JSON.toJSONString(r);
        System.out.println(jsonStr);
        resp.getWriter().write(jsonStr);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
