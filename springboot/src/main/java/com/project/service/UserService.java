package com.project.service;

import com.project.pojo.User;

public interface UserService {
    /**
     * 用户登录
     *
     * @param user
     * @return
     */

    User loginVerify(User user);

    void register(User user);

    User getByUsername(User user);

    String getEmailByUsername(String username);

    void updateUser(User user);

    void deregister(User user);
}
