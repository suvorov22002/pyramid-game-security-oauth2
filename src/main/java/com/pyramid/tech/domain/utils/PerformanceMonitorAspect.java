package com.pyramid.tech.domain.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 17/01/2025
 * Time: 21:39
 * Project Name: pyramid-game-security-oauth2
 */

@Aspect
@Slf4j
@Component
public class PerformanceMonitorAspect {

     @Around("execution(* com.pyramid.tech.domain.registration.service.imp.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
         long startTime = System.currentTimeMillis();

         Object result = joinPoint.proceed();

         long endTime = System.currentTimeMillis();
         long durationTime = endTime - startTime;

         log.info("{} ERROR --- Method {} executed in {} ms", LocalDateTime.now(), joinPoint.getSignature(), durationTime);

         return result;
     }
}
