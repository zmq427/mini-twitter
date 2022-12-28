package com.mq.test;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.junit.Test;

public class JSONtest {
    @Test
    public void test() {
        String str = "{\"tweet_text\":\"换个环境接空间可怜见了\",\"user_id\":\"1\",\"username\":\"zmq\",\"timestamp\":\"2022-12-28 11:25:51\"}";
        JSONObject obj = JSON.parseObject(str);
        System.out.println(obj);
    }
}
