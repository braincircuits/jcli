package com.m2891.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2891.constant.RCode;
import com.m2891.pojo.R;
import com.m2891.pojo.entity.SysUser;
import com.m2891.service.UserService;
import com.m2891.util.DtoUtil;
import com.m2891.util.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.savedrequest.NullRequestCache;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

import static com.m2891.constant.LoginConstant.loginUrl;

@EnableMethodSecurity
@Configuration
public class SecurityConfig
{
    public String[] getURLArray(String URLList)
    {
        String[] split = URLList.split(",");
        Stream<String> stream = Arrays.stream(split);
        String[] urls = new String[split.length];
        stream.map(String::trim).toList().toArray(urls);
        return urls;
    }

    private static String ignoringURL = """
            /error,
            /favicon.ico,
            /web/captcha,
            /web/config,
            /user/getUserByScore,
            /web/topicNodes,
            /api/**
            """.trim();
    private static String permitURL = "/user,/file/**,/abc";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService users()
    {
        return email -> userService.findUserByEmail(email);
    }

    ;

    @Bean
    SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
        http.cors();
//        http.addFilterBefore(new CaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                .loginProcessingUrl(loginUrl)
                .successHandler((request, response, authentication) -> {
                    SysUser principal = (SysUser) authentication.getPrincipal();
                    ServletUtils.renderString(response, objectMapper.writeValueAsString(R.success(DtoUtil.toUserDto(principal))));
                })
                .failureHandler((request, response, exception) -> {
                    ServletUtils.renderString(response, "登录失败" + exception.getMessage());
                })
        ;
        http.authorizeHttpRequests()
                .requestMatchers(permitURL.split(","))
                .permitAll()
                .anyRequest().authenticated();

        http
                .securityContext().securityContextRepository(new LocalSecurityContextRepository())
                .and()
                .requestCache().requestCache(new NullRequestCache())
                .and()
                .sessionManagement()
                .sessionAuthenticationStrategy(new NullAuthenticatedSessionStrategy())

        ;

        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler()
        {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException
            {
                response.getWriter().print("jjjjj");
            }
        });
//         未登录
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint()
        {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException
            {
                System.out.println(request.getMethod() + "=====" + request.getRequestURI());
                ServletUtils.renderString(response, objectMapper.writeValueAsString(R.error(RCode.A99999)));
            }
        });
        return http.build();
    }


    /**
     * 放行
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer()
    {
        return (web) -> web.ignoring()
                .requestMatchers(HttpMethod.OPTIONS)
                .requestMatchers(getURLArray(ignoringURL))
                ;
    }
}
