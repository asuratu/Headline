package com.heima.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author asura
 */
@Data
public class Type implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer tid;
    private String tname;
    @Version
    private Integer version;
    @TableLogic
    private Integer isDeleted;
}