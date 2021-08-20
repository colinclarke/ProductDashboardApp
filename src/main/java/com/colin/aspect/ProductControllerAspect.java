package com.colin.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductControllerAspect {

	Logger logger = LoggerFactory.getLogger(ProductControllerAspect.class);

	@Before(value = "execution(* com.colin.controller.ProductController.*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		logger.debug("Method: " + joinPoint.getSignature());
		for (Object o : joinPoint.getArgs()) {
			logger.debug("Argument: " + o);
		}
	}

	@AfterReturning(value = "execution(* com.colin.controller.ProductController.*(..))", returning = "returnValue")
	public void afterAdvice(JoinPoint joinPoint, Object returnValue) {
		logger.debug("Returns: " + returnValue);
	}

}
