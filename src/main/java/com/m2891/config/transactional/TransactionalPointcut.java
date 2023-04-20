package com.m2891.config.transactional;

import jakarta.transaction.Transactional;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

@Transactional
public class TransactionalPointcut extends StaticMethodMatcherPointcut
{
    @Override
    public boolean matches(Method method, Class<?> targetClass)
    {
        Class<Transactional> transactionalClass = Transactional.class;
        if (method.getAnnotation(transactionalClass) != null || targetClass.getAnnotation(transactionalClass) != null)
        {
            return true;
        }
        return false;
    }

}
