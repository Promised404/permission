package com.dpt.permission.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * 搜索日志DTO.
 *
 * @author 邓鹏涛
 * @date 2019/1/31 21:10
 */
@Getter
@Setter
@ToString
@Data
public class SearchLogDto {

    private Integer type; // LogType

    private String beforeSeg;

    private String afterSeg;

    private String operator;

    private Date fromTime; // yyyy-MM-dd HH:mm:ss

    private Date toTime; // yyyy-MM-dd HH:mm:ss


}
