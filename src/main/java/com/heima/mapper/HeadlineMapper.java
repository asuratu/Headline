package com.heima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.pojo.Headline;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author asura
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-10-11 15:29:37
 * @Entity com.heima.pojo.Headline
 */
public interface HeadlineMapper extends BaseMapper<Headline> {
    List<Headline> selectAllByHid(@Param("hid") Integer hid);
    
}




