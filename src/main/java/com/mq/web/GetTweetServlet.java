package com.mq.web;


import com.alibaba.fastjson2.JSON;
import com.mq.pojo.R;
import com.mq.pojo.Tweet;
import com.mq.service.TweetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getTweetServlet")
public class GetTweetServlet extends HttpServlet {

    private TweetService service = new TweetService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = Integer.parseInt(req.getParameter("userId"));
        System.out.println(userId);
        List<Tweet> tweets = service.getTweets(userId);
        System.out.println(tweets);

        R r = R.success(tweets);

        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(r));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
