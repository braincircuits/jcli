package com.m2891.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static com.m2891.constant.LoginConstant.sessionIdKey;

@Component
public class WebConfig
{


    private CorsConfiguration buildConfig()
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许任何域名使用
        corsConfiguration.addAllowedOriginPattern("*");
        // 允许任何请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setExposedHeaders(List.of(sessionIdKey));
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);

    }

//    @Bean
//    public MultipartConfigFactory multipartConfigFactory() {
//        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
//        //单个文件大小
//        multipartConfigFactory.setMaxFileSize(DataSize.of(1999L, DataUnit.BYTES));
//        //设置上传数据总大小
//        multipartConfigFactory.setMaxRequestSize(DataSize.of(1999L, DataUnit.BYTES));
//        return multipartConfigFactory;
//    }
}
