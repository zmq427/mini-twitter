package com.mq.pojo;

import com.alibaba.fastjson2.annotation.JSONField;

import java.util.Date;

public class Tweet {
    private Integer tweetId;
    @JSONField(name = "tweet_text")
    private String tweetText;
    @JSONField(name = "user_id")
    private Integer userId;
    @JSONField(name = "username")
    private String username;
    @JSONField(name = "timestamp")
    private Date timestamp;

    public Integer getTweetId() {
        return tweetId;
    }

    public void setTweetId(Integer tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", tweetText='" + tweetText + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
