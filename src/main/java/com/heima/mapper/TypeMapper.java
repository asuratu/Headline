package com.heima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.dto.TypeListItem;
import com.heima.pojo.Type;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author asura
 * @description 针对表【news_type】的数据库操作Mapper
 * @createDate 2024-10-11 15:29:37
 * @Entity com.heima.pojo.Type
 */
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> selectByTid(@Param("tid") Integer tid);

    List<TypeListItem> selectTypeList();
}




