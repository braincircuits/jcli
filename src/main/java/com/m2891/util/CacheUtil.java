package com.m2891.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CacheUtil
{
    static Cache<Object, Object> captchaCache =CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(Duration.ofSeconds(5))
            .removalListener(notification -> {
                System.out.println(notification.getCause());
                System.out.println(notification);
                System.out.println("2222222222222");
            })
            .build();

    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
    }
    public static void setCaptcha(String key,String value)
    {
        captchaCache.put(key,value);
    }


    public static String getCaptcha(String key)
    {
        try
        {
            return captchaCache.get(key, new Callable<String>()
            {
                @Override
                public String call() throws Exception
                {
                    return "String";
                }
            }).toString();
        } catch (ExecutionException e)
        {
            throw new RuntimeException(e);
        }
    }
}
