package com.project.mapper;

import com.project.pojo.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("select * from product order by id desc")
    List<Product> list();

    @Insert("insert into product(name, kind, seller, price, rest, cover_img_addr, detail_img_addr) " +
            "VALUES(#{name},#{kind},#{seller},#{price},#{rest},#{coverImgAddr},#{detailImgAddr})")
    void addProduct(Product product);

    @Select("select * from product where id = #{id}")
    Product getById(Integer id);

    @Delete("delete from product where id=#{id}")
    void delById(Integer id);
}
