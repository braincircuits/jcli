package com.m2891.config.web.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class EncoderRequestWrapper extends HttpServletRequestWrapper {
    public EncoderRequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
