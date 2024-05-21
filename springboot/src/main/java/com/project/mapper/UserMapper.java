package com.project.mapper;

import com.project.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where username=#{username} and password=#{password}")
    User getByUsernameAndPassword(User user);

    @Select("select * from user where username=#{username}")
    User getByUsername(User user);

    @Select("select email from user where username=#{username};")
    String getEmailByUsername(String username);

    @Insert("insert into user(username,password,email) values(#{username},#{password},#{email})")
    void addUser(User user);

    @Delete("delete from user where username=#{username}")
    void delUser(User user);

    @Update("update user set password=#{password},email=#{email} where id=#{id}")
    void updateUser(User user);
}
