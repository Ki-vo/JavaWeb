package com.project.service.impl;

import com.project.mapper.SalesMapper;
import com.project.pojo.Salesman;
import com.project.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    private SalesMapper salesMapper;

    @Override
    public Salesman loginVerify(Salesman salesman) {
        return salesMapper.getByUsernameAndPassword(salesman);
    }

    @Override
    public Salesman getById(Integer id) {
        return salesMapper.getById(id);
    }

    @Override
    public List<Salesman> getAllSales() {
        return salesMapper.list();
    }

    @Override
    public void addSales(Salesman salesman) {
        salesMapper.addSales(salesman);
    }

    @Override
    public void delSales(Integer id) {
        salesMapper.delSales(id);
    }

    @Override
    public void updateSales(Salesman salesman) {
        try {
            salesMapper.updateSales(salesman);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
