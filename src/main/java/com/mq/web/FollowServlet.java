package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mq.pojo.R;
import com.mq.service.UserService;
import com.mq.utils.RequestBodyParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/followServlet")
public class FollowServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody = RequestBodyParser.getBody(req);
        JSONObject jsonObject = JSON.parseObject(reqBody);
        System.out.println(jsonObject);
        Integer followerId = jsonObject.getInteger("followerId");
        Integer followeeId = jsonObject.getInteger("followeeId");
        service.follow(followerId, followeeId);

        R r = R.success("success");
        resp.getWriter().write(JSON.toJSONString(r));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
