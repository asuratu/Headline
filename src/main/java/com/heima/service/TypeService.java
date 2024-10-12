package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.pojo.Type;
import com.heima.utils.Result;

/**
 * @author asura
 * @description 针对表【news_type】的数据库操作Service
 * @createDate 2024-10-11 15:29:37
 */
public interface TypeService extends IService<Type> {

    Result<?> getIndex();
}
