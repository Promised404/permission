package com.dpt.permission.util;

import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 用户密码生成工具.
 *
 * @author 邓鹏涛
 * @date 2019/1/23 14:12
 */
public class PasswordUtil {

    public final static String[] word = {
            "a", "b", "c", "d", "e", "f", "g",
            "h", "j", "k", "m", "n",
            "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G",
            "H", "J", "K", "M", "N",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    };

    public final static String[] num = {
            "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String randomPassword() throws InterruptedException {
        StringBuffer stringBuffer = new StringBuffer();
        Thread.sleep(1);
        Random random = new Random(new Date().getTime());
        boolean flag = false;
        int length = random.nextInt(3) + 8;
        for (int i = 0; i < length; i++) {
            if (flag) {
                stringBuffer.append(num[random.nextInt(num.length)]);
            } else {
                stringBuffer.append(word[random.nextInt(num.length)]);
            }
            flag = !flag;
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(PasswordUtil.randomPassword());
        System.out.println(PasswordUtil.randomPassword());
        System.out.println(PasswordUtil.randomPassword());
    }
}
