package com.mq.service;

import com.mq.mapper.UserMapper;
import com.mq.pojo.User;
import com.mq.pojo.UserWithStatus;
import com.mq.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(String username, String password) {
        //2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.select(username, password);

        System.out.println(user);
        //4.释放资源
        sqlSession.close();

        return user;
    }

    public boolean register(String username, String password) {
        //2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByUsername(username);

        if (user == null) {
            userMapper.addUser(username, password);
            //4.提交事务
            sqlSession.commit();
        }

        //5.释放资源
        sqlSession.close();

        // 添加成功返回true
        return user == null;
    }

    public void follow(Integer follower_id, Integer followee_id) {
        //2.获取sqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.follow(follower_id, followee_id);

        sqlSession.commit();

        sqlSession.close();
    }

    public void unfollow(Integer follower_id, Integer followee_id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.unfollow(follower_id, followee_id);

        sqlSession.commit();
        sqlSession.close();
    }

    public List<UserWithStatus> getUsers(Integer followerId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 查询全部用户
        List<User> users = userMapper.selectAll();
        // 查询已关注的用户
        List<Integer> followedUsers = userMapper.selectFollowed(followerId);
        Set<Integer> set = new HashSet<>(followedUsers);
        // 给用户打上是否关注的标记
        List<UserWithStatus> uwslist = new ArrayList<>();
        for (User user: users) {
            UserWithStatus uws = new UserWithStatus();
            uws.setUsername(user.getUsername());
            uws.setUserId(user.getUserId());

            if (set.contains(user.getUserId())) {
                // 1表示被当前用户关注
                uws.setFollowed(1);
            } else {
                // 0表示未被当前用户关注
                uws.setFollowed(0);
            }
            uwslist.add(uws);
        }
        sqlSession.close();
        return uwslist;
    }

}
