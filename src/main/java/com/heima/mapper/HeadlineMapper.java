package com.heima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.heima.pojo.Headline;
import com.heima.pojo.dto.NewsListItem;
import com.heima.pojo.vo.TypeNewsReq;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author asura
 */
public interface HeadlineMapper extends BaseMapper<Headline> {
    List<Headline> selectAllByHid(@Param("hid") Integer hid);

    // 自定义分页查询方法
    IPage<NewsListItem> selectPageMap(IPage<NewsListItem> page,
                                      @Param("typeNewsReq") TypeNewsReq typeNewsReq);

    // 浏览量自增
    void addViews(@Param("hid") Integer id);

    @MapKey("hid")
    Map<String, Object> selectDetailMap(@Param("hid") Integer id);
}




