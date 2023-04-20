package com.m2891.config.hibernate;

import com.m2891.pojo.entity.BaseEntity;
import com.m2891.pojo.entity.SysUser;
import com.m2891.util.SecurityUtils;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;

public class MyInterceptor implements Interceptor
{

    private static final long serialVersionUID = 1L;
    
    

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException
    {
        if (entity instanceof BaseEntity)
        {
            for (int i = 0; i < propertyNames.length; i++)
            {
                if ("updateBy".equals(propertyNames[i]))
                {
                    // 获取当前用户信息并设置到“updateby”属性上
                    Integer currentUser = getCurrentUser();
                    state[i] = currentUser;
                    return true;
                }
                if ("createBy".equals(propertyNames[i]))
                {
                    // 获取当前用户信息并设置到“updateby”属性上
                    Integer currentUser = getCurrentUser();
                    state[i] = currentUser;
                    return true;
                }
            }
        }

        return false;
    }


    private Integer getCurrentUser()
    {
        SysUser sysUser = SecurityUtils.currentUser();
        return sysUser==null?null:sysUser.getUserId();
    }
    
    
}
