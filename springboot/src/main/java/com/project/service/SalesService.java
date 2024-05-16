package com.project.service;


import com.project.pojo.Salesman;

import java.util.List;

public interface SalesService {
    Salesman loginVerify(Salesman salesman);

    Salesman getById(Integer id);

    List<Salesman> getAllSales();

    void addSales(Salesman salesman);

    void delSales(Integer id);

    void updateSales(Salesman salesman);
}
