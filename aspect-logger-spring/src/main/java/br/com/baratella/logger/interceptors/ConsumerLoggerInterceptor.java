package br.com.baratella.logger.interceptors;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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

  @Around("consumerClassPointcut() || consumerMethodPointcut()")
  public Object doAroundIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
    return consumerLogger.logAroundConsumerMethod(joinPoint);
  }

}
