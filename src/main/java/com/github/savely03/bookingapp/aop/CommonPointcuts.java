package com.github.savely03.bookingapp.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommonPointcuts {

    @Pointcut("within(com.github.savely03.bookingapp.service.*)")
    public void isServiceLayer() {
    }

    @Pointcut("execution(public * *.*(..))")
    public void isAnyMethod() {
    }
}
