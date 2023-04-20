package com.m2891.util;

public class IPUtils
{
    public static String getCurrentRequestIp()
    {
        return ServletUtils.getRequest().getRemoteAddr();
    }
}
