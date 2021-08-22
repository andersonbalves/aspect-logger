package br.com.baratella.logger.interceptors;

import org.aspectj.lang.ProceedingJoinPoint;


public interface IProducerLogger {

  Object logAroundProducerMethod(ProceedingJoinPoint joinPoint) throws Throwable;
}
