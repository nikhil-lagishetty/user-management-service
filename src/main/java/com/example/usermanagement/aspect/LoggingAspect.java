package com.example.usermanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging execution time, input arguments, and responses of REST controller methods.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /**
     * Pointcut that matches all methods within classes annotated with {@link org.springframework.web.bind.annotation.RestController}.
     */
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerMethods() {}

    /**
     * Logs the execution time, method name, input arguments, and response for all REST controller methods.
     *
     * @param joinPoint the join point representing the method execution.
     * @return the result of the method execution.
     * @throws Throwable if an exception occurs during method execution.
     */
    @Around("controllerMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        logger.info("Executing {} with arguments: {}", methodName, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - startTime;
            logger.info("Completed {} in {} ms | Response: {}", methodName, executionTime, result);
            return result;
        } catch (Exception e) {
            long executionTime = System.currentTimeMillis() - startTime;
            logger.error("Error in {} | Execution time: {} ms", methodName, executionTime, e);
            throw e;
        }
    }
}
