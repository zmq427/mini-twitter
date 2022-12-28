package com.mq.service;

import com.mq.mapper.UserMapper;
import com.mq.pojo.User;
import com.mq.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

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

    public List<User> getUsers() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        sqlSession.close();
        return users;
    }

}
