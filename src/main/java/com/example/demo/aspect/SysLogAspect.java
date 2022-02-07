package com.example.demo.aspect;

import com.example.demo.common.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLogAspect {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);

    @Pointcut("@annotation(com.example.demo.common.SysLog)")
    public void invokePointCut() {
        // empty
    }

    @Around("invokePointCut() && @annotation(sysLog)")
    public Object around(ProceedingJoinPoint point, SysLog sysLog) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        LOGGER.info("Method {} takes time: {}ms.", point.getSignature(), time);
        LOGGER.info(sysLog.value());

        return result;
    }
}
