package com.heima.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author asura
 */
@Data
public class Headline implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
    private Integer publisher;
    private Integer pageViews;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    @Version
    private Integer version;
    @TableLogic
    private Integer isDeleted;


}