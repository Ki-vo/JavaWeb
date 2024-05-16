package com.project.mapper;

import com.project.pojo.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginLogMapper {
    @Insert("insert into login_log (role, ip_address, login_time, exit_time) values (#{role},#{ipAddress},#{loginTime},#{exitTime});")
    void addLoginLog(LoginLog loginLog);

    @Select("select * from login_log;")
    void getAllLoginLog();
}
