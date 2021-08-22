package br.com.baratella.logger.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IControllerLogger {

  Object logAroundControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable;

}
