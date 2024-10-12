package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.pojo.Headline;
import com.heima.pojo.vo.PublishNewsReq;
import com.heima.pojo.vo.TypeNewsReq;
import com.heima.utils.Result;
import jakarta.validation.Valid;

/**
 * @author asura
 * @description 针对表【news_headline】的数据库操作Service
 * @createDate 2024-10-11 15:29:37
 */
public interface HeadlineService extends IService<Headline> {

    Result<?> getTypeNews(TypeNewsReq req);

    Result<?> getNewsDetail(Integer id);

    Result<?> publish(@Valid PublishNewsReq publishNewsReq);

    Result<?> getItem(Integer id);
}
