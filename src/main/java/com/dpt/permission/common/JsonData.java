package com.dpt.permission.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 *处理请求json数据工具.
 *
 * @author 邓鹏涛
 * @date 2019/1/20 18:28
 */
@Getter
@Setter
public class JsonData {

    private boolean ret;
    private String msg;
    private Object data;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    /**
     * 请求成功，携带提示信息和数据
     * @param object
     * @param msg
     * @return
     */
    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }

    /**
     * 请求成功，只携带数据
     * @param object
     * @return
     */
    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return jsonData;
    }

    /**
     * 请求成功，只携带提示信息
     * @param msg
     * @return
     */
    public static JsonData success(String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.msg = msg;
        return jsonData;
    }

    /**
     * 请求成功，不携带任何信息
     * @return
     */
    public static JsonData success() {
        return new JsonData(true);
    }

    /**
     * 请求失败，并携带失败信息
     * @param msg
     * @return
     */
    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.msg = msg;
        return jsonData;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("ret", this.ret);
        result.put("msg", this.msg);
        result.put("data", this.data);
        return result;
    }
}
