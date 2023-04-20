package com.m2891.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.aop.framework.AopContext;

import java.util.concurrent.locks.ReentrantLock;

//@Component
@Data
@AllArgsConstructor
public class CaptchaProperties
{
    private String captchaUrl = "";

    public static void main(String[] args)
    {
        new Thread().start();
        AopContext.currentProxy();  
        new ReentrantLock();
    }
}
