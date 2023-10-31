package com.m2891.config.web.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DecoderRequestWrapper extends HttpServletRequestWrapper {
    private final ByteArrayInputStream bodyByteInputStream;
    @SneakyThrows
    public DecoderRequestWrapper(HttpServletRequest request) {
        super(request);
        ServletInputStream inputStream = request.getInputStream();
        String body = IOUtils.toString(inputStream);
        bodyByteInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }
}
