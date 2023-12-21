package com.project.service.impl;

import com.project.mapper.UserMapper;
import com.project.pojo.User;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginVerify(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }


    @Override
    public void register(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User getByUsername(User user) {
        return userMapper.getByUsername(user);
    }

    @Override
    public void deregister(User user) {
        try {
            userMapper.delUser(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
