package com.project.mapper;

import com.project.pojo.Order;
import com.project.pojo.Revenue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("insert into `order` (user_id, product_id, product_price, quantity, timestamp) values (#{userId},#{productId},#{productPrice},#{purchaseNum},#{timeStamp});")
    void addOrder(Order order);

    @Delete("delete from `order` where id=#{id};")
    void deleteOrder(Integer id);

    @Select("select * from `order` order by id desc")
    List<Order> getAllOrders();

    @Select("select * from `order` inner join product on `order`.product_id = product.id where cate_id=#{cateId} order by `order`.id desc")
    List<Order> getOrdersByCateId(Integer cateId);

    @Select("select product_id,product.cate_id,sum(quantity) as quantity,sum(quantity*product_price) as total_price " +
            "from `order` join product on `order`.product_id = product.id group by product_id order by product_id;")
    List<Revenue> getTotalRevenue();
}