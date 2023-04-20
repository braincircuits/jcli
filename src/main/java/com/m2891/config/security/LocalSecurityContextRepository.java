package com.m2891.config.security;

import com.m2891.util.id.IDUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.DeferredSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static com.m2891.constant.LoginConstant.sessionIdKey;

public class LocalSecurityContextRepository implements SecurityContextRepository
{
    public static final ConcurrentHashMap<String, Object> map = new ConcurrentHashMap();
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
            .getContextHolderStrategy();
    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder)
    {
        HttpServletRequest request = requestResponseHolder.getRequest();
        return loadDeferredContext(request).get();
    }

    @Override
    public DeferredSecurityContext loadDeferredContext(HttpServletRequest request)
    {
        String id = request.getHeader(sessionIdKey);
        Supplier<SecurityContext> supplier = () -> id == null ? null : (SecurityContext) map.get(id);
        return new SupplierDeferredSecurityContext(supplier, this.securityContextHolderStrategy);
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response)
    {
        String sessionIdValueNew = IDUtils.getSessionId();
        response.setHeader(sessionIdKey, sessionIdValueNew);
        map.put(sessionIdValueNew, context);
    }

    @Override
    public boolean containsContext(HttpServletRequest request)
    {
        String sessionId = request.getHeader("sessionId");
        return sessionId == null ? false : map.containsKey(sessionId);
    }
}
