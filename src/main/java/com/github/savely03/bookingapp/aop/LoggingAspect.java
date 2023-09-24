package com.github.savely03.bookingapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    // Данные pointcuts можно вынести в одельный аспект
    @Pointcut("within(com.github.savely03.bookingapp.service.*)")
    public void isServiceLayer() {
    }

    @Pointcut("execution(public * *.*(..))")
    public void isAnyServiceMethod() {
    }

    @Around("isServiceLayer() && isAnyServiceMethod()")
    public Object addLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = getMethodName(joinPoint);
        String targetClassName = getTargetClassName(joinPoint);

        log.info("Method {} was invoked in class - {}",
                methodName,
                targetClassName
        );

        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error(String.format("%s - %s", e.getClass().getName(), e.getMessage()), e);
            throw e;
        } finally {
            log.info("Method {} completed work in class - {}",
                    methodName,
                    targetClassName
            );
        }
    }

    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    private String getTargetClassName(JoinPoint joinPoint) {
        return joinPoint.getTarget().getClass().getName();
    }
}
