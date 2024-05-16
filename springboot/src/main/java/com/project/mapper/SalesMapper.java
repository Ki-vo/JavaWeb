package com.project.mapper;

import com.project.pojo.Salesman;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SalesMapper {

    @Select("select * from seller")
    List<Salesman> list();

    @Select("select * from seller where id=#{id}")
    Salesman getById(Integer id);

    @Select("select type from seller where id=#{id}")
    Integer getTypeById(Integer id);

    @Insert("insert into seller(password,type) values(#{password},#{type})")
    void addSales(Salesman salesman);

    @Delete("delete from seller where id=#{id}")
    void delSales(Integer id);

    @Select("select * from seller where id=#{id} and password=#{password}")
    Salesman getByUsernameAndPassword(Salesman salesman);

    @Update("update seller set password=#{password},type=#{type} where id=#{id}")
    void updateSales(Salesman salesman);
}
