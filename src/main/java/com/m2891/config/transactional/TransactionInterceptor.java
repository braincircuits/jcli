package com.m2891.config.transactional;

import com.m2891.util.HibernateSession;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Component
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class TransactionInterceptor implements MethodInterceptor
{
    @Nullable
    @Override
    public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable
    {
        return HibernateSession.transaction(session -> {
            try
            {
                return invocation.proceed();
            } catch (Throwable e)
            {
                throw new RuntimeException(e);
            }
        });
    }
}
