package com.dpt.permission.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 搜索日志参数.
 *
 * @author 邓鹏涛
 * @date 2019/1/31 21:04
 */
@Getter
@Setter
@ToString
public class SearchLogParam {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private String fromTime; // yyyy-MM-dd HH:mm:ss

    private String toTime;
}
