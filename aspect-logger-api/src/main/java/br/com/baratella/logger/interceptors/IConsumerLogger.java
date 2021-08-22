package br.com.baratella.logger.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IConsumerLogger {

  Object logAroundConsumerMethod(ProceedingJoinPoint joinPoint) throws Throwable;
}
