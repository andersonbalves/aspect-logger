package br.com.baratella.logger.interceptors;

import org.aspectj.lang.JoinPoint;

public interface IServiceLogger {

  void logBeforeMethod(JoinPoint joinPoint);

  void logAfterMethod(JoinPoint joinPoint, Object retVal);

  void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable;

}
