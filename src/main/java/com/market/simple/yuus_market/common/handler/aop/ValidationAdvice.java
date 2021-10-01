package com.market.simple.yuus_market.common.handler.aop;



import com.market.simple.yuus_market.common.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.market.simple.yuus_market.domains..*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult bindingResult = (BindingResult) arg;

                if (bindingResult.hasErrors()) {
                   String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
                    throw  new CustomValidationException(message);
                }

            }
        }
        return proceedingJoinPoint.proceed();
    }
}
