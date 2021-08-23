package br.com.baratella.logger.interceptors;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class ProducerLoggerInterceptor {

  private final IProducerLogger producerLogger;

  @Pointcut("execution(* org.springframework.kafka.core.KafkaTemplate.*(..))")
  private void producerMethodPointcut() {
  }

  @Before("producerMethodPointcut()")
  public void doLogBeforeMethod(JoinPoint joinPoint) throws Throwable {
    producerLogger.logBeforeMethod(joinPoint);
  }

  @AfterReturning(pointcut = "producerMethodPointcut()", returning = "retVal")
  public void doLogAfterMethod(JoinPoint joinPoint, Object retVal) throws Throwable {
    producerLogger.logAfterMethod(joinPoint, retVal);
  }

  @AfterThrowing(pointcut = "producerMethodPointcut()", throwing = "ex")
  public void doLogAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    producerLogger.logAfterThrowing(joinPoint, ex);
  }

}
