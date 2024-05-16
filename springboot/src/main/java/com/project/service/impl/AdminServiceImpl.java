package com.project.service.impl;

import com.project.mapper.AdminMapper;
import com.project.pojo.Admin;
import com.project.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin loginVerify(Admin admin) {
        return adminMapper.getByIdAndPassword(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void updateAdmin(Admin admin) {
        try {
            adminMapper.updatePassword(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
