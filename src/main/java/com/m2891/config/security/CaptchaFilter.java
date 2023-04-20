package com.m2891.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2891.constant.RCode;
import com.m2891.pojo.R;
import com.m2891.util.CacheUtil;
import com.m2891.util.ServletUtils;
import com.m2891.util.SpringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.m2891.constant.LoginConstant.loginUrl;

/**
 * 登录验证码
 */
public class CaptchaFilter extends OncePerRequestFilter
{
    private final String url = loginUrl;
    private final ObjectMapper objectMapper = SpringUtil.getBean(ObjectMapper.class);
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(loginUrl,
            "POST");
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        if (DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request))
        {
            String captchaId = request.getParameter("captchaId");
            String captchaCode = request.getParameter("captchaCode");
            if (StringUtils.isBlank(captchaCode))
            {
                ServletUtils.renderString(response, objectMapper.writeValueAsString(R.error(RCode.A10001)));
                return;
            }
            String serverCaptchaCode = CacheUtil.getCaptcha(captchaId);
            if (!captchaCode.equals(serverCaptchaCode))
            {
                ServletUtils.renderString(response, objectMapper.writeValueAsString(R.error(RCode.A10001)));
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
