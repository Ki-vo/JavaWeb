package com.project.mapper;


import com.project.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);

    @Select("select * from user where username=#{username}")
    User getByUsername(User user);

    @Insert("insert into user(username,password,email) values(#{username},#{password},#{email})")
    void addUser(User user);

    @Delete("delete from user where username=#{username}")
    void delUser(User user);
}
