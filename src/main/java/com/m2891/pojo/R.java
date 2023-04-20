package com.m2891.pojo;

import com.m2891.constant.RCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class R<T>
{
    private int code;
    private String message;
    private T data;

    public R(RCode rCode, T date)
    {
        this.code = rCode.getCode();
        this.message = rCode.getMsg();
        this.data = date;
    }

    public static <T> R<T> success()
    {
        return success(null);
    }

    public static <T> R<T> success(T date)
    {
        return new R<>(RCode.A0, date);
    }

    public static <T> R<T> error()
    {
        return error(RCode.A1, null);
    }

    public static <T> R<T> error(T date)
    {
        return new R<>(RCode.A1, date);
    }

    public static <T> R<T> error(RCode rCode)
    {
        return error(rCode, null);
    }

    public static <T> R<T> error(RCode rCode, T date)
    {
        return new R<>(rCode, date);
    }
}
