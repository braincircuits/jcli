package com.m2891.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SpringUtil implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        SpringUtil.applicationContext =applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        Assert.notNull(applicationContext,"此处不适用");
        return applicationContext;
    }
    
    public static<T> T getBean(Class<T> tClass)
    {
        return getApplicationContext().getBean(tClass);
    }
}
