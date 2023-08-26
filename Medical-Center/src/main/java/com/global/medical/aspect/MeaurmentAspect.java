package com.global.medical.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class MeaurmentAspect {
	
	
	Logger log = LoggerFactory.getLogger(MeaurmentAspect.class);
	
	@Around(value = "execution(* com.global.medical.service..*(..))")
	public Object logTime(ProceedingJoinPoint  joinPoint) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder("KPI:");
		sb.append("[").append(joinPoint.getKind()).append("]\tfor: ").append(joinPoint.getSignature())
				.append("\twithArgs: ").append("(").append(StringUtils.join(joinPoint.getArgs(), ",")).append(")");
		sb.append("\ttook: ");
		Object returnValue = joinPoint.proceed();                 // The 'joinPoint.proceed()' call ensures that the original method is executed, allowing us to measure its execution time and log relevant information. The return value of the method is stored in the 'returnValue' variable for later use.
		log.info(sb.append(System.currentTimeMillis() - startTime).append(" ms.").toString());
		
		return returnValue;
	}
	
	
}
