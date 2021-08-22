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
public class ServiceLoggerInterceptor {

  private final IServiceLogger serviceLogger;

  @Pointcut("@within(org.springframework.stereotype.Service)")
  private void servicePointcut() {
  }

  @Around("servicePointcut()")
  public Object doAroundIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
    return serviceLogger.logAroundServiceMethod(joinPoint);
  }

}
