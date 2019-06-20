package com.dpt.permission.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.dpt.permission.param.TestVo;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.type.TypeReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json装换工具.
 *
 * @author 邓鹏涛
 * @date 2019/1/21 13:49
 */
@Slf4j
public class JsonMapper {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // config
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
    }

    public static <T> String object2String(T src) {
        if (src == null) {
            return null;
        }
        try {
            return src instanceof String ? (String) src : objectMapper.writeValueAsString(src);
        } catch (Exception e) {
            log.warn("parse object to String exception, error:{}", e);
            return null;
        }
    }

    public static <T> T string2Obj(String src, TypeReference<T> typeReference) {
        if (src == null || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? src : objectMapper.readValue(src,typeReference));
        } catch (Exception e){
            log.warn("parse String to Object exception, String:{}, TypeReference<T>, error", src, typeReference.getType(), e);
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("姓名", "小明");
        map.put("sex", "男");
        System.out.println(JSONUtils.toJSONString(map));
        HashMap<String, String> map1 = string2Obj("{" + "\"姓名\"" + ":" + "\"小明\"" + "," + "\"sex\"" + ":" + "\"男\"" + "}", new TypeReference<HashMap<String, String>>() {
        });
        for (String str : map1.keySet()) {
            System.out.println(str + ":" + map1.get(str));
        }
    }
}
