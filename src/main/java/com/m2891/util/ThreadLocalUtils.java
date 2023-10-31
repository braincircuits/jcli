package com.m2891.util;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalUtils
{
    private static final ThreadLocal<Map<String, Object>> THREAD_CONTEXT = ThreadLocal.withInitial(HashMap::new);

    public static void put(final String key, final Object value) {
        THREAD_CONTEXT.get().put(key, value);
    }

    public static void remove(final String key) {
        THREAD_CONTEXT.get().remove(key);
    }

    public static Object get(final String key) {
        return THREAD_CONTEXT.get().get(key);
    }

    public static void clear() {
        THREAD_CONTEXT.remove();
    }
}
