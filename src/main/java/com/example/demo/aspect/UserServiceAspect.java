package com.example.demo.aspect;

import com.example.demo.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 对方法的切面处理
 */
@Aspect
@Component
public class UserServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);

    @Pointcut("execution(* com.example.demo.service.UserService.getUserById(..)) && args(id)")
    private void pointcut(Long id) {
        // empty
    }

    @Before(value = "pointcut(id)", argNames = "joinPoint,id")
    public void before(JoinPoint joinPoint, Long id) {
        LOGGER.info("Before " + joinPoint.getSignature());
        LOGGER.info("userId: " + id);
    }

    @After(value = "pointcut(id)", argNames = "joinPoint,id")
    public void after(JoinPoint joinPoint, Long id) {
        LOGGER.info("After " + joinPoint.getSignature());
    }

    @AfterReturning(value = "pointcut(id)", returning = "user", argNames = "joinPoint,id,user")
    public void afterReturning(JoinPoint joinPoint, Long id, User user) {
        LOGGER.info("After " + joinPoint.getSignature() + " returning");
        LOGGER.info("Get user: " + user.getUsername());
    }

    @AfterThrowing(value = "pointcut(id)", throwing = "ex", argNames = "joinPoint,id,ex")
    public void afterThrowing(JoinPoint joinPoint, Long id, Throwable ex) {
        LOGGER.info("During " + joinPoint.getSignature());
        LOGGER.info("Throwing: " + ex.getMessage());
    }
}
