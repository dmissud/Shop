package org.dbs.shop.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);

	@Pointcut("within(@(@org.springframework.stereotype.Component *) *)")
	private void developerIsDoingSomething() {  /* PointCut */
	}

	@Before("developerIsDoingSomething()")
	public void log(final JoinPoint jp) {
		final StringBuilder toLog = new StringBuilder("logging ").append(jp.getSignature().toLongString())
				.append(" with args: [");
		final Object[] args = jp.getArgs();
		for (final Object arg : args) {
			toLog.append(arg).append(", ");
		}
		toLog.append("]");
		LOGGER.info(toLog.toString());
	}

	@Around("developerIsDoingSomething()")
	public Object profile(final ProceedingJoinPoint pjp) throws Throwable {
		final long start = System.currentTimeMillis();
		final Object output = pjp.proceed();
		final long elapsedTime = System.currentTimeMillis() - start;
		LOGGER.info("Method execution time: " + elapsedTime + " milliseconds.");
		return output;
	}
}
