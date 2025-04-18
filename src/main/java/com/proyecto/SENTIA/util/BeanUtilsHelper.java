package com.proyecto.SENTIA.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;

public class BeanUtilsHelper {

    private BeanUtilsHelper() {

    }

    public static String[] getNullPropertyNames(Object source) {
        return Arrays.stream(BeanUtils.getPropertyDescriptors(source.getClass()))
                .map(PropertyDescriptor::getName)
                .filter(name -> {
                    try {
                        PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(source.getClass(), name);
                        return pd != null && pd.getReadMethod().invoke(source) == null;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .toArray(String[]::new);
    }

}
