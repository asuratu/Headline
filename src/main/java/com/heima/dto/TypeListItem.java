package com.heima.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author asura
 */
@Data
public class TypeListItem implements Serializable {
    private Integer tid;
    private String tname;
}