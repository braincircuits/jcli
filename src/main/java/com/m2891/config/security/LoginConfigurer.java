package com.m2891.config.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class LoginConfigurer<H extends HttpSecurityBuilder<H>> extends
        AbstractAuthenticationFilterConfigurer<H, LoginConfigurer<H>, UsernamePasswordAuthenticationFilter>
{
    @Override
    protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl)
    {
        return null;
    }
}
