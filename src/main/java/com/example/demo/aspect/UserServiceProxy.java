package com.example.demo.aspect;

import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceProxy implements InvocationHandler {

    private IUserService iUserService;

    public IUserService createProxy(IUserService iUserService) {
        return (IUserService) Proxy.newProxyInstance(UserServiceProxy.class.getClassLoader(), iUserService.getClass().getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        UserService userServiceImpl = (UserService) this.iUserService;
        Object result = null;
        // 执行方法
        result = method.invoke(iUserService, args);

        return result;
    }

}
