package com.example.demo.service;

import com.example.demo.enums.Sex;
import com.example.demo.model.User;
import com.example.demo.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransferService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransferService.class);

    /**
     * 无依赖注入方法/静态方法
     * java传统反射方式
     *
     * @param tClass
     * @param tMethod
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T invokeTargetMethod(String tClass, String tMethod, List<Object> params) throws Exception {
        Class<?> clazz = Class.forName(tClass);

        Class<?>[] objectClass = new Class[params.size()];
        for (int i = 0; i < params.size(); i++) {
            objectClass[i] = params.get(i).getClass();
        }
        Method method = params.isEmpty() ?
                clazz.getMethod(tMethod) : clazz.getMethod(tMethod, objectClass);
        LOGGER.info("SUCCESSFULLY GET METHOD: {}, CLASS:{}", tMethod, tClass);
        return params.isEmpty() ?
                (T) method.invoke(clazz.newInstance()) : (T) method.invoke(clazz.newInstance(), params.toArray());
    }

    /**
     * 包含依赖注入
     * 通过自定义SpringContextUtil获取bean
     * fixme 目前只能调用无参或者简单类型参数的函数
     *
     * @param tClass
     * @param tMethod
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T invokeTargetMethodByBean(String tClass, String tMethod, List<Object> params) throws Exception {
        Class<?> clazz = Class.forName(tClass);
        Object bean = SpringContextUtil.getBean(clazz);

        Class<?>[] objectClass = new Class[params.size()];
        for (int i = 0; i < params.size(); i++) {
            objectClass[i] = params.get(i).getClass();
        }

        return params.isEmpty() ? (T) bean.getClass().getDeclaredMethod(tMethod).invoke(bean) :
                (T) bean.getClass().getDeclaredMethod(tMethod, objectClass).invoke(bean, params.toArray());
    }

    public static void main(String[] args) {
        TransferService transferService = new TransferService();
        User user = new User((long)1, "Hamlet", Sex.MALE);
        ArrayList param = new ArrayList();
        param.add("null");
        param.add(2);
        param.add(user);
        try {
            Object s = transferService.invokeTargetMethod("com.example.demo.service.TransferService", "test1", param);
            System.out.println(s);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public User test1(String s1, Integer t, User user) {
//        System.out.println("test1" + s1 + t);
//        return "test1" + s1 + t + user.getUsername();
        user.setUsername("Ophelia");
        user.setSex(Sex.FEMALE);
        return user;
    }
}
