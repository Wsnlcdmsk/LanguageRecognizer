package com.project.langrecognizer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Aspect for logging method calls, returns, exceptions, and execution time.
 */
@Component
@Aspect
@Slf4j
public final class LoggingAspect {

    /**
     * Pointcut to match all methods in the service and controller packages.
     */
    @Pointcut("execution(* com.project.langrecognizer.service.*.*(..))"
            + " || execution(* com.project.langrecognizer.controller.*.*(..))")
    public void allMethods() {
    }

    /**
     * Pointcut to match methods annotated with LoggingAnnotation.
     */
    @Pointcut("@annotation("
            + "com.project.langrecognizer.aspect.LoggingAnnotation)")
    public void methodsWithAspectAnnotation() {
    }

    @Pointcut("@annotation("
            + "com.project.langrecognizer.aspect.LoggingAnnotationException)")
    public void error() {
    }
    /**
     * Advice to log method calls.
     * @param joinPoint The join point representing the method call.
     */
    @Before("methodsWithAspectAnnotation()")
    public void logMethodCall(final JoinPoint joinPoint) {
        logInfo(joinPoint, "Method called");
    }

    /**
     * Advice to log method returns.
     * @param joinPoint The join point representing the method call.
     * @param result The result returned by the method.
     */
    @AfterReturning(pointcut = "methodsWithAspectAnnotation()",
            returning = "result")
    public void logMethodReturn(final JoinPoint joinPoint,
                                final Object result) {
        logInfo(joinPoint, "Method return", "returned: " + result);
    }


    @Before("error()")
    public void logException(final JoinPoint joinPoint) {
        log.error("123ц{}: {}.{} with args: {} {}",
                "Exception in", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()),
                "cause: ");
    }

    /**
     * Advice to log method execution time.
     * @param joinPoint The join point representing the method call.
     * @return The result returned by the method.
     * @throws Throwable Thrown if an error occurs during method execution.
     */
    @Around("methodsWithAspectAnnotation()")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint)
            throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logInfo(joinPoint, "Method executed", "in " + executionTime + "ms");
        return proceed;
    }

    /**
     * Utility method to log information.
     * @param joinPoint The join point representing the method call.
     * @param message The log message.
     * @param additionalInfo Additional information to log.
     */
    private void logInfo(final JoinPoint joinPoint,
                         final String message, final String additionalInfo) {
        Object[] args = joinPoint.getArgs();
        String fullClassName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        log.info("{}: {}.{} with args: {} {}",
                message, fullClassName, methodName, Arrays.toString(args),
                additionalInfo);
    }

    /**
     * Utility method to log information without additional info.
     * @param joinPoint The join point representing the method call.
     * @param message The log message.
     */
    private void logInfo(final JoinPoint joinPoint, final String message) {
        logInfo(joinPoint, message, "");
    }

}
