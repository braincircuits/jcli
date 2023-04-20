package com.m2891.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CaptchaDto
{
    private String captchaId;
    private String captchaBase64;
}
