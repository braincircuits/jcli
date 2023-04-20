package com.m2891.dao;

import com.m2891.pojo.entity.SysUser;
import com.m2891.util.HibernateSession;
import org.hibernate.jpa.HibernateHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao
{
    @Override
    public SysUser findUserByEmail(String email)
    {
        return HibernateSession.transaction(session -> {
            List<SysUser> sysUsers = session
                    .createQuery(
                            "from SysUser su where su.email = :email"
                            , SysUser.class)
                    .setHint(HibernateHints.HINT_READ_ONLY, true)
                    .setParameter("email", email)
                    .getResultList();
            return sysUsers.isEmpty() ? null : sysUsers.get(0);
        });
    }


    @Override
    public SysUser findUserByNickName(String nickName)
    {
        return HibernateSession.transaction(session -> {
            List<SysUser> sysUsers = session.createQuery("from SysUser su where su.nickName=:nickName", SysUser.class)
                    .setReadOnly(true)
                    .setParameter("nickName", nickName)
                    .getResultList();
            return sysUsers.isEmpty() ? null : sysUsers.get(0);
        });
    }

    @Override
    public List<SysUser> byScore()
    {
        return HibernateSession.transaction(session -> {
            return session.createQuery("from SysUser su order by su.score desc", SysUser.class)
                    .setReadOnly(true)
                    .setFirstResult(0)
                    .setMaxResults(10)
                    .getResultList()
                    ;
        });
    }
}
