package com.heima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.pojo.User;
import com.heima.utils.Result;

/**
 * @author asura
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return Result
     */
    Result<?> login(User user);

    Result<?> getUserInfo(String token);

    Result<?> checkUsername(String username);

    Result<?> register(User user);
}
