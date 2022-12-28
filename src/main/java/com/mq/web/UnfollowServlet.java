package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mq.service.UserService;
import com.mq.utils.RequestBodyParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UnfollowServlet extends HttpServlet {
    private UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqBody = RequestBodyParser.getBody(req);
        JSONObject jsonObject = JSON.parseObject(reqBody);
        Integer followerId = jsonObject.getInteger("followerId");
        Integer followeeId = jsonObject.getInteger("followeeId");
        service.unfollow(followerId, followeeId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
