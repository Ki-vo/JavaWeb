package com.project.mapper;

import com.project.pojo.UserLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserLogMapper {

    @Select("select * from user_log inner join product on user_log.product_id = product.id where action=#{action} and product.cate_id=#{cate_id}")
    List<UserLog> getLogByAction(Integer action, Integer cate_id);

    @Insert("insert into user_log (user_id, product_id, action, timestamp,browse_duration) values (#{userId},#{productId},#{action},#{timeStamp},#{browseDuration});")
    void addBrowserLog(UserLog userLog);

    @Insert("insert into user_log (user_id, product_id, action, purchase_sum ,timestamp) values (#{userId},#{productId},#{action},#{purchaseSum},#{timeStamp});")
    void addPurchaseLog(UserLog userLog);
}
