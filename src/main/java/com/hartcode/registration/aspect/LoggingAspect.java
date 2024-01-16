package com.hartcode.registration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    // Setup logger
    private final Logger myLogger = Logger.getLogger(getClass().getName());

    // Setup pointcut declarations
    @Pointcut("execution(* com.hartcode.registration.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.hartcode.registration.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("execution(* com.hartcode.registration.entity.*.*(..))")
    private void forEntityPackage() {
    }

    @Pointcut("execution(* com.hartcode.registration.security.*.*(..))")
    private void forSecurityPackage() {
    }

    @Pointcut("execution(* com.hartcode.registration.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forEntityPackage() || forSecurityPackage() || forServicePackage()")
    private void forAppLogging() {
    }

    // Add @Before logging advice
    @Before("forAppLogging()")
    public void before(JoinPoint joinPoint) {

        // Display the method we are calling
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("-----> (@Before) Calling method: " + method);

        // Get the argument
        Object[] args = joinPoint.getArgs();

        // Loop through and display arguments
        for(Object tempArgs: args) {
            myLogger.info("-----> (Before) Argument: " + tempArgs);
        }
    }

    // Add @AfterReturning logging advice
    @AfterReturning(pointcut = "forAppLogging()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        // Display method we are returning from
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("-----> (AfterReturning) From method: " + method);

        // Display data returned
        myLogger.info("-----> (AfterReturning) Result: " + result);
    }
}
