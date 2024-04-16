package com.project.langrecognizer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@Aspect
@Slf4j
public final class LoggingAspect {

    @Pointcut("execution(* com.project.langrecognizer.service.*.*(..))"
            + " || execution(* com.project.langrecognizer.controller.*.*(..))")
    public void allMethods() {
    }

    @Pointcut("@annotation(com.project.langrecognizer.aspect.LoggingAnnotation)")
    public void methodsWithAspectAnnotation() {

    }

    @Before("methodsWithAspectAnnotation()")
    public void logMethodCall(final JoinPoint joinPoint) {
        logInfo(joinPoint, "Method called");
    }

    @AfterReturning(pointcut = "methodsWithAspectAnnotation()", returning = "result")
    public void logMethodReturn(final JoinPoint joinPoint, final Object result) {
        logInfo(joinPoint, "Method return", "returned: " + result);
    }

    @AfterThrowing(pointcut = "allMethods()", throwing = "exception")
    public void logException(final JoinPoint joinPoint, final Throwable exception) {
        logInfo(joinPoint, "Exception in", "cause: " + exception.getMessage());
    }

    @Around("methodsWithAspectAnnotation()")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logInfo(joinPoint, "Method executed", "in " + executionTime + "ms");
        return proceed;
    }

    private void logInfo(final JoinPoint joinPoint, final String message, final String additionalInfo) {
        Object[] args = joinPoint.getArgs();
        String fullClassName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}: {}.{} with args: {} {}", message, fullClassName, methodName, Arrays.toString(args),
                additionalInfo);
    }

    private void logInfo(final JoinPoint joinPoint, final String message) {
        logInfo(joinPoint, message, "");
    }

}
