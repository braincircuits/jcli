package com.m2891.util;

import com.m2891.pojo.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class SecurityUtils
{
    
    public static SysUser currentUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication.getPrincipal() instanceof SysUser sysUser)
        {
            return sysUser;
        }
        return null;
    }

    public static List<GrantedAuthority> createAuthorityList(String... authorities)
    {
        return AuthorityUtils.createAuthorityList(authorities);
    }
}
