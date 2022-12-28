package com.mq.web;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mq.pojo.R;
import com.mq.service.TweetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;

import static com.mq.utils.RequestBodyParser.getBody;


@WebServlet("/tweetServlet")
public class TweetServlet extends HttpServlet {
    private TweetService service = new TweetService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数
        String request_str = getBody(req);
        JSONObject jsonObject = JSON.parseObject(request_str);
        System.out.println(jsonObject);

        String tweetText = jsonObject.getString("tweet_text");
        Integer userId = jsonObject.getInteger("user_id");
        String username = jsonObject.getString("username");
        Timestamp timestamp = Timestamp.valueOf(jsonObject.getString("timestamp"));

        // 调用service向数据库添加数据
        service.tweet(tweetText, userId, username, timestamp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
