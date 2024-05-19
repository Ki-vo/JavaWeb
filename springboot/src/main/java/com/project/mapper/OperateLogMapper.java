package com.project.mapper;

import com.project.pojo.OperateLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {
    @Insert("insert into operation_log (user_id, role, ip_address, operation_time, operation) values (#{userId},#{role},#{ipAddress},#{operationTime},#{operation});")
    void addOperateLog(OperateLog operateLog);

    @Select("select * from operation_log;")
    List<OperateLog> getAllOperateLog();

    @Delete("delete from operation_log where id=#{id};")
    void deleteOperateLogById(int id);
}
