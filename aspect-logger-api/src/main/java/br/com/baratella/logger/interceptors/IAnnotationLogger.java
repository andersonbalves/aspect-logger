package br.com.baratella.logger.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;

public interface IAnnotationLogger {

  Object logAroundAnnotatedMethod(ProceedingJoinPoint joinPoint) throws Throwable;
}
