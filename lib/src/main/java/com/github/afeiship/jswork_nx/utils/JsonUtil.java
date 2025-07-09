package com.github.afeiship.jswork_nx.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

public class JsonUtil {
    public static final String EMPTY_JSON = "{}"; // 空的JSON数据
    public static final String EMPTY_JSON_ARRAY = "[]"; // 空的数组(集合)数据
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss"; // 默认的日期，时间字段的格式化模式

    private static Gson gson;

    private static Gson getGson() {
        if (gson == null) {
            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat(DEFAULT_DATE_PATTERN).serializeNulls();
            gson = builder.create();
        }
        return gson;
    }

    /*--------------------------------------------------------------------------
    | 将给定的目标对象根据所指定的条件参数转换成JSON格式的字符串
    --------------------------------------------------------------------------*/
    public static String toJson(Object target) {
        return toJson(target, null);
    }


    /**
     * 将给定的目标对象根据所指定的条件参数转换成JSON格式的字符串。 该方法转换发生错误时，不会抛出任何异常。若发生错误时，对象返回"{}"，集合或数组对象返回 "[]"，
     * 其本基本类型，返回相应的基本值
     *
     * @param target     目标对象。
     * @param targetType 目标对象的类型
     *                   <p>
     *                   //@param builder 可定制的Gson构建器
     * @return 目标对象的JSON格式的字符串
     */
    public static String toJson(Object target, Type targetType) {
        if (target == null) return EMPTY_JSON;
        String result = EMPTY_JSON;
        try {
            if (targetType == null) {
                result = getGson().toJson(target);
            } else {
                result = getGson().toJson(target, targetType);
            }
        } catch (Exception e) {
            if (target instanceof Collection<?> || target instanceof Iterator<?> || target instanceof Enumeration<?> || target.getClass().isArray()) {
                result = EMPTY_JSON_ARRAY;
            }
        }
        return result;
    }


    public static <T> T fromJson(String json, TypeToken<T> token) {
        return fromJson(json, token.getType());
    }

    public static <T> T fromJson(String json, Type type) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return getGson().fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将给定的JSON字符串转换成指定的类型对象
     *
     * @param json 给定的JSON字符串
     * @param cls  要转换的目标类
     *             <p>
     *             //@param datePattern 日期格式 //removed!!!
     * @return 给定的JSON字符串表示的指定的类型对象
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        TypeToken<T> typeToken = TypeToken.get(cls);
        return fromJson(json, typeToken);
    }
}
