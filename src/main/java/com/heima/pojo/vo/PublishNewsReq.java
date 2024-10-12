package com.heima.pojo.vo;

import jakarta.validation.constraints.Max;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;

/**
 * @author asura
 */
@Getter
@Setter
public class PublishNewsReq {
    // 必填, 最多 10 个字符
    @Required(message = "标题不能为空")
    @Max(value = 10, message = "标题不能超过10个字符")
    private String title = "";

    // 必填, 最多 1000 个字符
    @Required(message = "内容不能为空")
    @Max(value = 1000, message = "内容不能超过1000个字符")
    private String article = "";

    // 默认类型 0
    @Required(message = "类型不能为空")
    private Integer type = 0;

    @Override
    public String toString() {
        return "PublishNewsReq{" +
                "title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", type=" + type +
                '}';
    }
}
