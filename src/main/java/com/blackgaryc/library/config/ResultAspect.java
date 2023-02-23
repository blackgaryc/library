package com.blackgaryc.library.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class ResultAspect {
    private static final Logger log = LoggerFactory.getLogger(ResultAspect.class);

//    @Around("within(com.blackgaryc.library.controller.*)")
//    public Object controllerAop(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//        log.info(signature.toLongString() + " income parameters :" + Arrays.toString(args));
//        long startTime = System.currentTimeMillis();
//        Object proceedResult = joinPoint.proceed();
//        log.info(signature.toLongString() + " processed results : " + proceedResult);
//        long endTime = System.currentTimeMillis();
//        log.info(signature.toLongString() + "time spend : " + (endTime - startTime));
//        return proceedResult;
//    }
}
