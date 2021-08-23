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
public class ConsumerLoggerInterceptor {

  private final IConsumerLogger consumerLogger;

  @Pointcut("@within(org.springframework.kafka.annotation.KafkaListener)")
  private void consumerClassPointcut() {
  }

  @Pointcut("execution(@org.springframework.kafka.annotation.KafkaListener * *(..))")
  private void consumerMethodPointcut() {
  }

  @Before("consumerClassPointcut() || consumerMethodPointcut()")
  public void doLogBeforeMethod(JoinPoint joinPoint) throws Throwable {
    consumerLogger.logBeforeMethod(joinPoint);
  }

  @AfterReturning(pointcut = "consumerClassPointcut() || consumerMethodPointcut()", returning = "retVal")
  public void doLogAfterMethod(JoinPoint joinPoint, Object retVal) throws Throwable {
    consumerLogger.logAfterMethod(joinPoint, retVal);
  }

  @AfterThrowing(pointcut = "consumerClassPointcut() || consumerMethodPointcut()", throwing = "ex")
  public void doLogAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    consumerLogger.logAfterThrowing(joinPoint, ex);
  }

}
