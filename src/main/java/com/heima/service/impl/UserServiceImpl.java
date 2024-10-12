package com.heima.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.mapper.UserMapper;
import com.heima.pojo.User;
import com.heima.service.UserService;
import com.heima.utils.JwtHelper;
import com.heima.utils.MD5Util;
import com.heima.utils.Result;
import com.heima.utils.ResultCodeEnum;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author asura
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;
    private final JwtHelper jwtHelper;

    public UserServiceImpl(UserMapper userMapper, JwtHelper jwtHelper) {
        this.userMapper = userMapper;
        this.jwtHelper = jwtHelper;
    }

    /**
     * 用户登录
     *
     * @param user 用户对象
     * @return 登录结果
     */
    @Override
    public Result<?> login(User user) {
        // 查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        // 用户不存在
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        // 登录成功
        if (!StringUtils.isEmpty(user.getUserPwd()) && loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            // 生成 token
            String token = jwtHelper.createToken(loginUser.getUid().longValue());
            // 封装 token 到 result
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);

            System.out.println("token_before: " + token);

            boolean expiration = jwtHelper.isExpiration(token);
            System.out.println("expiration: " + expiration);

            return Result.ok(map);
        }

        // 密码错误
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result<?> getUserInfo(String token) {
        System.out.println("token: " + token);

        // 验证 token 是否过期
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }

        // 解析 token
        Long userId = jwtHelper.getUserId(token);

        // 查询用户信息
        User user = userMapper.selectById(userId);

        // 封装结果
        Map<String, Object> map = new HashMap<>();
        map.put("userinfo", user);
        return Result.ok(map);
    }

    @Override
    public Result<?> checkUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        if (userMapper.selectCount(queryWrapper) > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result<?> register(User user) {
        // 检查用户名是否已被注册
//        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(User::getNickName, user.getUsername());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if (userMapper.selectCount(queryWrapper) > 0) {
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        // 注册用户
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




