package com.project.service;

import com.project.pojo.Admin;

public interface AdminService {
    Admin loginVerify(Admin admin);

    Admin getById(Integer id);

    void updateAdmin(Admin admin);
}
