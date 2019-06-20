package com.dpt.permission.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 等级关系工具类(仅此项目适用).
 *
 * @author 邓鹏涛
 * @date 2019/1/21 20:12
 */
public class LevelUtil {

    private final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    /**
     * 计算等级.
     *
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }

}
