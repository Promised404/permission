package com.dpt.permission.util;

import com.google.common.base.Splitter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字符串处理工具.
 *
 * @author 邓鹏涛
 * @date 2019/1/29 12:58
 */
public class StringUtil {

    /**
     * 字符串处理.
     * @param str
     * @return
     */
    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
        return strList.stream()
                .map(strItem -> Integer.parseInt(strItem))
                .collect(Collectors.toList());
    }

}
