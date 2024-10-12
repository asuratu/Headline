package com.heima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.heima.utils.ResultCodeEnum;
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

    @Override
    public Result<?> updateItem(Headline data) {
        // 使用 乐观锁, 必须要先查询一下
        Headline headline = headlineMapper.selectById(data.getHid());
        if (headline == null) {
            return Result.build(null, ResultCodeEnum.NOT_FOUND);
        }

        // 这个会使 version 的乐观锁失效
//        BeanUtils.copyProperties(data, headline);

        // 赋值
        headline.setTitle(data.getTitle());
        headline.setArticle(data.getArticle());
        headline.setType(data.getType());

        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result<?> deleteItem(Integer id) {
        QueryWrapper<Headline> wrapper = new QueryWrapper<>();
        wrapper.eq("hid", id);
        wrapper.eq("publisher", UserUtil.getUserId());

        // 只允许删除自己的文章
        if (headlineMapper.selectCount(wrapper) == 0) {
            return Result.build(null, ResultCodeEnum.NOT_FOUND);
        }

        // 逻辑删除
        headlineMapper.delete(wrapper);
        
        return Result.ok(null);
    }

}




