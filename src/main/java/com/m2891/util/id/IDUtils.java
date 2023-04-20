package com.m2891.util.id;

public class IDUtils
{
    public static final IdGenerator idGenerator = new ThreadLocalRandomIdGenerator();
    // 更安全方式生成id
    public static final IdGenerator secureIdGenerator = new SecureRandomIdGenerator();

    public static String getSessionId()
    {
        return secureIdGenerator.getId();
    }
    

    public static String getUUID()
    {
        return idGenerator.getId();
    }

    public static String getUUID(boolean isSecurity)
    {
        return secureIdGenerator.getId();
    }
}
