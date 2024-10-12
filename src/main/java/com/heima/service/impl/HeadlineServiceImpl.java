package com.heima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.dto.TypeNewsReq;
import com.heima.mapper.HeadlineMapper;
import com.heima.pojo.Headline;
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

        QueryWrapper<Headline> queryWrapper = new QueryWrapper<>();

        System.out.println(req);

        // 关键词
        if (req.getKeyWords() != null && !req.getKeyWords().isEmpty()) {
            queryWrapper.like("title", req.getKeyWords());
        }

        // 类型
        if (req.getType() != 0) {
            queryWrapper.eq("type", req.getType());
        }

        // id 倒序
        queryWrapper.orderByDesc("hid");

        // 分页
        Page<Headline> page = new Page<>(req.getPage(), req.getSize());

        return Result.page(headlineMapper.selectPage(page, queryWrapper));
    }
}




