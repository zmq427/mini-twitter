package com.mq.service;

import com.mq.mapper.TweetMapper;
import com.mq.mapper.UserMapper;
import com.mq.pojo.Tweet;
import com.mq.pojo.User;
import com.mq.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TweetService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public void tweet(String tweetText, Integer userId, String username, Timestamp timestamp) {
        //2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        TweetMapper tweetMapper = sqlSession.getMapper(TweetMapper.class);
        tweetMapper.addTweet(tweetText, userId, username, timestamp);

        //提交事务
        sqlSession.commit();

        //5.释放资源
        sqlSession.close();
    }

    public List<Tweet> getTweets(Integer userId, String username) {
        //2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        TweetMapper tweetMapper = sqlSession.getMapper(TweetMapper.class);
        List<Tweet> resultTweets = tweetMapper.getTweets(userId, username);

        sqlSession.close();

        return resultTweets;
    }
}
