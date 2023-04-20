package com.m2891.config.hibernate;

import com.m2891.util.HibernateSession;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class HibernateConfig
{
    @Bean
    public SessionFactory sessionFactory()
    {
        return HibernateSession.getSessionFactory();
    }
}
