package com.mq.mapper;

import com.mq.pojo.Tweet;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;

public interface TweetMapper {
    void addTweet(@Param("tweetBody") String tweetBody, @Param("userId") Integer userId,
                   @Param("username") String username, @Param("timestamp") Timestamp timestamp);
}
