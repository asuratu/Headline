package com.heima.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author asura
 */
@Data
public class NewsListItem implements Serializable {
    private Integer hid;
    private String title;
    private Integer type;
    private Integer pageViews;
    private Integer pastHours;
    private Integer publisher;
}