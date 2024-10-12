package com.heima.utils;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author asura
 */
public class UserUtil {
    public static Long getUserId() {
        // 从当前请求上下文中获取 request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request;
        Long userId = null;
        if (attributes != null) {
            request = attributes.getRequest();
            // 从 request 中获取 user_id
            userId = (Long) request.getAttribute("userId");
        }
        return userId;
    }
}
