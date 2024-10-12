package com.heima.pojo.vo;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

/**
 * @author asura
 */
@Getter
@Setter
public class TypeNewsReq {
    // 默认空字符串
    private String keyWords = "";
    // 默认类型 0
    private Integer type = 0;
    // 页数默认 1，必须大于等于 1
    @Min(1)
    private Integer page = 1;
    @Min(10)
    private Integer size = 10;

    @Override
    public String toString() {
        return "TypeNewsReq{" +
                "keyWords='" + keyWords + '\'' +
                ", type=" + type +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
