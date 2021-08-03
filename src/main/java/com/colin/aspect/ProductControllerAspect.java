package com.colin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductControllerAspect {

	@Before(value = "execution(* com.colin.controller.ProductController.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Method: " + joinPoint.getSignature());
		for (Object o : joinPoint.getArgs()) {
			System.out.println("Argument: " + o);
		}
	}

	@AfterReturning(value = "execution(* com.colin.controller.ProductController.*(..))", returning = "returnValue")
	public void afterAdvice(JoinPoint joinPoint, Object returnValue) {
		System.out.println("Returns: " + returnValue);
	}

}
