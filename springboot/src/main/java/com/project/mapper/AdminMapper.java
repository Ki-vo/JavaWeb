package com.project.mapper;

import com.project.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where id=#{id};")
    Admin getById(Integer id);

    @Select("select * from admin where id=#{id} and password=#{password}")
    Admin getByIdAndPassword(Admin admin);

    @Update("update admin set password=#{password} where id=#{id}")
    void updatePassword(Admin admin);
}
