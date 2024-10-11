package com.heima.utils;


import lombok.Getter;

/**
 * @author asura
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "success"),
    USERNAME_ERROR(501, "usernameError"),
    PASSWORD_ERROR(503, "passwordError"),
    NOTLOGIN(504, "notLogin"),
    USERNAME_USED(505, "userNameUsed");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}