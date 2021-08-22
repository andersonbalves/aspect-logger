package br.com.baratella.logger.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IServiceLogger {

  Object logAroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable;

}
