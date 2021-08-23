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
public class ServiceLoggerInterceptor {

  private final IServiceLogger serviceLogger;

  @Pointcut("@within(org.springframework.stereotype.Service)")
  private void servicePointcut() {
  }

  @Before("servicePointcut()")
  public void doLogBeforeMethod(JoinPoint joinPoint) throws Throwable {
    serviceLogger.logBeforeMethod(joinPoint);
  }

  @AfterReturning(pointcut = "servicePointcut()", returning = "retVal")
  public void doLogAfterMethod(JoinPoint joinPoint, Object retVal) throws Throwable {
    serviceLogger.logAfterMethod(joinPoint, retVal);
  }

  @AfterThrowing(pointcut = "servicePointcut()", throwing = "ex")
  public void doLogAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    serviceLogger.logAfterThrowing(joinPoint, ex);
  }

}
