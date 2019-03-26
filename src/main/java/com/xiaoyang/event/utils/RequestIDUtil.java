package com.xiaoyang.event.utils;

import java.util.Random;

/**
 *生成每次请求的唯一requestID，使用RequestRecordFilter初始化
 */
public class RequestIDUtil {

    private static final ThreadLocal<Integer> REQUEST_ID = new ThreadLocal<Integer>();
    
    private static final ThreadLocal<Long> START = new ThreadLocal<Long>();

    public static void start() {
        START.set(System.currentTimeMillis());
    }
    
    public static Long end() {
        Long end = System.currentTimeMillis();
        Long cost = end - (START.get() == null ? 0 : START.get());
        return cost;
    }
    
    public static void initRequestId() {
        REQUEST_ID.set(new Random().nextInt(Integer.MAX_VALUE));
    }

    public static Integer getRequestId() {
        return REQUEST_ID.get();
    }
}
