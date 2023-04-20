package com.m2891.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateSession
{
    public final static ThreadLocal<Session> currentSession = new ThreadLocal<>();

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    private final static SessionFactory sessionFactory = new MetadataSources
            (
            new StandardServiceRegistryBuilder()
                    .configure()// configures settings from hibernate.cfg.xml
                    .build()
            )
            .buildMetadata()
            .getSessionFactoryBuilder()
            .build();

    public static void transaction(Consumer<Session> consumer)
    {
        boolean isStart = false;
        Session session = currentSession.get();
        Transaction transaction = null;
        try
        {
            if (session == null)
            {
                isStart = true;
                session = sessionFactory.openSession();
                currentSession.set(session);
            }
            transaction = session.getTransaction();
            if (isStart)
            {
                transaction.begin();
            }
            consumer.accept(session);
            if (isStart)
            {
                transaction.commit();
            }
        } catch (Exception e)
        {
            if (isStart && transaction != null)
            {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally
        {
            if (isStart)
            {
                currentSession.remove();
            }
            if (isStart && session != null)
            {
                session.close();
            }
        }
    }

    public static <R> R transaction(Function<Session, R> function)
    {
        boolean isStart = false;

        Session session = currentSession.get();
        Transaction transaction = null;
        try
        {
            if (session == null)
            {
                isStart = true;
                session = sessionFactory.openSession();
                currentSession.set(session);
            }
            transaction = session.getTransaction();
            if (isStart)
            {
                transaction.begin();
            }
            R apply = function.apply(session);
            if (isStart)
            {
                transaction.commit();
            }
            return apply;
        } catch (Exception e)
        {
            if (isStart && transaction != null)
            {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally
        {
            if (isStart)
            {
                currentSession.remove();
            }
            if (isStart && session != null)
            {
                session.close();
            }
        }
    }

    public static void transaction(Runnable runnable)
    {
        transaction(session -> {
           runnable.run(); 
        });
    }
}
