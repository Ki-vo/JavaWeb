package com.project.mapper;

import com.project.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product order by id desc")
    List<Product> list();

    @Select("select * from product where cate_id=#{type} order by id desc;")
    List<Product> getListByType(Integer type);

    @Select("select * from product order by id desc limit 8")
    List<Product> getHotProduct();

    @Insert("insert into product(name, cate_id, seller, price, rest, cover_img) " +
            "VALUES(#{name},#{cateId},#{seller},#{price},#{rest},#{coverImg})")
    void addProduct(Product product);

    @Update("update product set name=#{name},seller=#{seller},price=#{price},rest=#{rest},cover_img=#{coverImg} where id=#{id} ")
    void updateProduct(Product product);

    @Select("select * from product where id = #{id}")
    Product getById(Integer id);

    @Select("select cover_img from product where id=#{id}")
    String getCoverImgAddr(Integer id);

    @Delete("delete from product where id=#{id}")
    void delById(Integer id);

}
