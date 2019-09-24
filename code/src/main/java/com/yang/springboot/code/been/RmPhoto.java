package com.yang.springboot.code.been;

import lombok.Data;

import java.util.Date;

/**
 * This is Description
 *
 * @author yang
 * @date 2019/09/12
 */
@Data
public class rmPhoto {
    private long photoId;
    private int type;
    private String address;
    private Date createTime;
    private Date modifyTime;
    private int source;
}
