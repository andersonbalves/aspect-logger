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
public class ProducerLoggerInterceptor {

  private final IProducerLogger producerLogger;

  @Pointcut("execution(* org.springframework.kafka.core.KafkaTemplate.*(..))")
  private void producerMethodPointcut() {
  }

  @Around("producerMethodPointcut()")
  public Object doAroundIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
    return producerLogger.logAroundProducerMethod(joinPoint);
  }

}
