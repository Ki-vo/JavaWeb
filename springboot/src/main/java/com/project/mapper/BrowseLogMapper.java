package com.project.mapper;

import com.project.pojo.BrowseLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrowseLogMapper {

    @Select("select * from browse_log inner join product on browse_log.product_id = product.id where product.cate_id=#{cate_id}")
    List<BrowseLog> getLogByCateId(Integer cate_id);

    @Insert("insert into browse_log (user_id, product_id,browse_duration,timestamp) values (#{userId},#{productId},#{browseDuration},#{timeStamp});")
    void addBrowserLog(BrowseLog browseLog);
}
