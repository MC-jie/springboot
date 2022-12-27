package com.atguigu.user.mapper;

import com.atguigu.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mr jie
 * @create 2022-50-26-20:50
 */
@Service
@SuppressWarnings("all")
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        User user = userMapper.findById(id);
        user.setUsername("userservice-demo01");
        return user;
    }
}
