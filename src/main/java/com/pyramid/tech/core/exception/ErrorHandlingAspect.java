package com.pyramid.tech.core.exception;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 17/01/2025
 * Time: 20:51
 * Project Name: pyramid-game-security-oauth2
 */

@Aspect
@Slf4j
@Component
public class ErrorHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingAspect.class);

    @AfterThrowing(pointcut = "execution(* com.pyramid.tech.domain.registration.service.imp.*.*(..))", throwing = "exception")
    public void logException(Exception exception) {
        log.info("{} ERROR --- An exception occured \uD83D\uDE00: {}", LocalDateTime.now(), exception.getMessage());
    }

}
