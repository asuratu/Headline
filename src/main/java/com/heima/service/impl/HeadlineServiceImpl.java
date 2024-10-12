package com.heima.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.HeadlineMapper;
import com.heima.pojo.Headline;
import com.heima.pojo.dto.NewsListItem;
import com.heima.pojo.vo.TypeNewsReq;
import com.heima.service.HeadlineService;
import com.heima.utils.Result;
import org.springframework.stereotype.Service;

/**
 * @author asura
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {

    private final HeadlineMapper headlineMapper;

    public HeadlineServiceImpl(HeadlineMapper headlineMapper) {
        this.headlineMapper = headlineMapper;
    }

    @Override
    public Result<?> getTypeNews(TypeNewsReq req) {
        // 分页
        Page<NewsListItem> page = new Page<>(req.getPage(), req.getSize());

        return Result.page(headlineMapper.selectPageMap(page, req));
    }

    @Override
    public Result<?> getNewsDetail(Integer id) {
        // 浏览量+1
        headlineMapper.addViews(id);

        // 查询新闻详情
        return Result.ok(headlineMapper.selectDetailMap(id).values().stream().findFirst());
    }
}




