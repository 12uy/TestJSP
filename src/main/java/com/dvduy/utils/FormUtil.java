package com.dvduy.utils;


import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

public class FormUtil { // dùng để lấy dữ liệu từ form gửi lên server
    public static  <T> T toModel(Class<T> tClass, HttpServletRequest request) {
        T getParam = null;
        try {
            getParam =  tClass.getDeclaredConstructor().newInstance();
            BeanUtils.populate(getParam, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return getParam;
    }
}
