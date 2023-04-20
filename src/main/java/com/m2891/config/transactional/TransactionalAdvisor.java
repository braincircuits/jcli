package com.m2891.config.transactional;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

public class TransactionalAdvisor extends AbstractBeanFactoryPointcutAdvisor
{
    private TransactionalPointcut transactionalPointcut = new TransactionalPointcut();
    @Override
    public Pointcut getPointcut()
    {
        return transactionalPointcut;
    }
}
