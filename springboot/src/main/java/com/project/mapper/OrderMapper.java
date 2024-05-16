package com.project.mapper;

import com.project.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order` (user_id, product_id, product_price, purchase_num, timestamp) values (#{userId},#{productId},#{productPrice},#{purchaseNum},#{timeStamp});")
    void addOrder(Order order);

    @Delete("delete from `order` where id=#{id};")
    void deleteOrder(Integer id);

    @Select("select * from `order` order by id desc")
    List<Order> getAllOrders();

    @Select("select * from `order` inner join product on `order`.product_id = product.id where cate_id=#{cateId} order by `order`.id desc")
    List<Order> getOrdersByCateId(Integer cateId);
}