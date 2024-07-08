package icu.hilin.core.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseVO {

    private Long id;
    private Date createTime;
    private Date updateTime;

}
