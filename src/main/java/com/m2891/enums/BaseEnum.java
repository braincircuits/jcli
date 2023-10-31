package com.m2891.enums;

import java.io.Serializable;

public interface BaseEnum extends Serializable {
    int getCode();
    String getDesc();

    default String getStyle() {
        return "";
    }
}
