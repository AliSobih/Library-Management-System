package com.library.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.library.service.*.*(..))")
    public void logBeforeMethodCall() {
        logger.info("Method is about to be called.");
    }

    @AfterReturning(pointcut = "execution(* com.library.service.*.*(..))", returning = "result")
    public void logAfterMethodCall(Object result) {
        logger.info("Method execution finished with result: {}", result);
    }
}
