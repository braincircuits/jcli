package com.m2891.util;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.function.Supplier;

public class IBeanUtils extends BeanUtils {

    public static <S, T> T copy(S source, Supplier<T> target) {
        if (source == null) {
            return null;
        }
        T t = target.get();
        copyProperties(source, t);
        return t;
    }

    public static <S, T> List<T> copyList(List<S> sources, Supplier<T> target) {
        if (CollectionUtils.isEmpty(sources)) {
            return Collections.emptyList();
        }
        ArrayList<T> targetList = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            targetList.add(t);
        }
        return targetList;
    }

    public static String beanToStr(Object message, String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        if (message == null) {
            return joiner.toString();
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(message);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            System.out.println(propertyDescriptor.getName());
            System.out.println(beanWrapper.getPropertyValue(propertyDescriptor.getName()));
            System.out.println("===========");
        }
        return "";
    }

    public static Map<String,Object> beanToMap(Object message) {
        return BeanMap.create(message);
    }

}
