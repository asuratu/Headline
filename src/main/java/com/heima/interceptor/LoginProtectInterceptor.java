package com.heima.interceptor;


import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.utils.JwtHelper;
import com.heima.utils.Result;
import com.heima.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author asura
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    private final JwtHelper jwtHelper;

    public LoginProtectInterceptor(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) throws Exception {

        String token = request.getHeader("Authorization");
        if (StringUtils.isEmpty(token) || jwtHelper.isExpiration(token)) {
            Result<Object> result = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);
            //拦截
            return false;
        } else {
            // 获取用户id
            Long userId = jwtHelper.getUserId(token);
            request.setAttribute("userId", userId);
            //放行
            return true;
        }
    }
}
