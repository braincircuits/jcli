package com.m2891.config.transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component  
public class TransactionalConfig
{
    @Bean
    public TransactionalAdvisor transactionalAdvisor(TransactionInterceptor transactionInterceptor)
    {
        TransactionalAdvisor transactionalAdvisor = new TransactionalAdvisor();
        transactionalAdvisor.setAdvice(transactionInterceptor);
        transactionalAdvisor.setOrder(100);
        return transactionalAdvisor;
    }
}
