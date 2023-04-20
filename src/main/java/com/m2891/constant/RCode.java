package com.m2891.constant;

public enum RCode
{
    /**
     * 成功
     */
    A0("success",0),
    /**
     * 错误
     */
    A1("error",1),
    A10001("验证码错误",10001),
    /**
     * 主题节点不存在
     */
    A10002("主题节点不存在", 10002),
    A99999("未登录",99999)
    ;
    RCode(String msg, int code)
    {
        this.msg = msg;
        this.code = code;
    }

    private String msg;
    private int code;

    public String getMsg()
    {
        return msg;
    }

    public int getCode()
    {
        return code;
    }
}
