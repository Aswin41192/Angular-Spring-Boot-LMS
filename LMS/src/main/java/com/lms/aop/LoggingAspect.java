package com.lms.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger logger = LoggerFactory.getLogger("APPLOGGER");
	private static final Logger errorLogger = LoggerFactory.getLogger("ERRORLOGGER");


	@Before("execution(* com.lms..*(..))")
	public void logBefore(JoinPoint jp) {
		logger.debug("Executing the method --> {} --> with parameters",jp.getSignature(),jp.getArgs());
	}
	
	@AfterReturning(pointcut = "execution(* com.lms..*(..))",returning = "returnValue")
	public void logAfterReturning(JoinPoint jp,Object returnValue) {
		logger.info("Returning from the method --> {} with return value --> {}",jp.getSignature(),returnValue);
	}
	
	@AfterThrowing(pointcut = "execution(* com.lms..*(..))",throwing = "e")
	public void logExceptionTrace(Exception e) {
		errorLogger.error(e.getMessage(), e);
	}
}
