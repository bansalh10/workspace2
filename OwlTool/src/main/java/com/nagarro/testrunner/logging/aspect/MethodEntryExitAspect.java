/*
 * @Copyright Nagarro Software Pvt. Ltd
 * 
 */
package com.nagarro.testrunner.logging.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodEntryExitAspect {

	/**
	 * 
	 */
	private static final Logger logger = Logger.getLogger(MethodEntryExitAspect.class);

	/**
	 * 
	 */
	@Pointcut("execution(* com.nagarro.testrunner.manager.service.*.*(..))")
	private void aroundServiceLoggingPointcut(){
		
	}
	
	@Pointcut("execution(* com.nagarro.testrunner.manager.manager.*.*(..))")
	private void aroundManagerLoggingPointcut(){
		
	}
	
	@Pointcut("execution(* com.nagarro.testrunner.client.*.*(..))")
	private void aroundClientLoggingPointcut(){
		
	}
	
	@Pointcut("execution(* com.nagarro.testrunner.manager.request*.*(..))")
	private void aroundRequestLoggingPointcut(){
		
	}
	
	@Pointcut("execution(* com.nagarro.testrunner.manager.response*.*(..))")
	private void aroundResponseLoggingPointcut(){
		
	}
	
	@Before("aroundServiceLoggingPointcut() && aroundManagerLoggingPointcut() && aroundClientLoggingPointcut() && aroundRequestLoggingPointcut() && aroundResponseLoggingPointcut()")
	public void logBeforeMethod(JoinPoint joinPoint){
		logger.info(" ********** " + joinPoint.getTarget().getClass() + " : "+ joinPoint.getSignature().getName() + " : Method Entry  **********");
	}
	
	@After("aroundServiceLoggingPointcut() && aroundManagerLoggingPointcut() && aroundClientLoggingPointcut() && aroundRequestLoggingPointcut() && aroundResponseLoggingPointcut()")
	public void logAfterMethod(JoinPoint joinPoint){
		logger.info(" ********** " + joinPoint.getTarget().getClass() + " : "+ joinPoint.getSignature().getName() + " : Method Exit  **********");
	}
	
}
