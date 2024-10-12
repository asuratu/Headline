package com.heima.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.HeadlineMapper;
import com.heima.pojo.Headline;
import com.heima.pojo.dto.NewsListItem;
import com.heima.pojo.dto.NewsUpdateItem;
import com.heima.pojo.vo.PublishNewsReq;
import com.heima.pojo.vo.TypeNewsReq;
import com.heima.service.HeadlineService;
import com.heima.utils.Result;
import com.heima.utils.UserUtil;
import org.springframework.beans.BeanUtils;
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
        // 使用 userId
        System.out.println("User ID: " + UserUtil.getUserId());

        // 浏览量+1
        headlineMapper.addViews(id);

        // 查询新闻详情
        return Result.ok(headlineMapper.selectDetailMap(id).values().stream().findFirst());
    }

    @Override
    public Result<?> publish(PublishNewsReq publishNewsReq) {
        // 获取当前用户ID
        Long userId = UserUtil.getUserId();

        // 属性转换
        Headline headline = new Headline();
        BeanUtils.copyProperties(publishNewsReq, headline);

        // 设置发布者信息
        headline.setPublisher(Math.toIntExact(userId));
        headline.setPageViews(0);

        // 插入新闻
        headlineMapper.insert(headline);

        return Result.ok(null);
    }

    @Override
    public Result<?> getItem(Integer id) {
        Headline headline = headlineMapper.selectById(id);
        NewsUpdateItem item = new NewsUpdateItem();
        BeanUtils.copyProperties(headline, item);

        return Result.ok(item);
    }
}




