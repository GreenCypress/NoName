package com.fjgcxy.xyb.auto.clock.in.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class BeanCopyUtils {

    public static <T> T copy(Object o, Class<T> tClass) {
        try {
            T result = tClass.newInstance();
            BeanUtils.copyProperties(o, result);
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
