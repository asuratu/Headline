package com.heima.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author asura
 */
@Data
@Component // 这里声明这个注解的目的是什么？2
@ConfigurationProperties(prefix = "jwt.token")
public class JwtHelper {

    // 有效时间,单位毫秒 1000毫秒 == 1秒
    private long expiration;
    // 当前程序签名秘钥
    private String signKey;

    // 生成token字符串
    public String createToken(Long userId) {
        System.out.println("expiration = " + expiration);
        System.out.println("signKey = " + signKey);
        //单位分钟
        return Jwts.builder()
                .setSubject("YYGH-USER")
                //单位分钟
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000 * 60))
                .claim("userId", userId)
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    //从token字符串获取userid
    public Long getUserId(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId.longValue();
    }


    //判断token是否有效
    public boolean isExpiration(String token) {
        try {
            //没有过期，有效，返回false
            return Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
        } catch (Exception e) {
            //过期出现异常，返回true
            System.out.println(e.getMessage());
            return true;
        }
    }
}
