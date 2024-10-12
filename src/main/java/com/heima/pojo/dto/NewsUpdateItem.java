package com.heima.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author asura
 */
@Data
public class NewsUpdateItem implements Serializable {
    private Integer hid;
    private String title;
    private String article;
    private Integer type;
}