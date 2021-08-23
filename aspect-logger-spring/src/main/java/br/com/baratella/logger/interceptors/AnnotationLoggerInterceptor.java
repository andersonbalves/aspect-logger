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
public class AnnotationLoggerInterceptor {

  private final IAnnotationLogger annotationLogger;

  @Pointcut("execution(@br.com.baratella.logger.entity.annotation.LogMethod * *(..))")
  private void annotatedMethodPointcut() {
  }

  @Before("annotatedMethodPointcut()")
  public void doLogBeforeMethod(JoinPoint joinPoint) throws Throwable {
    annotationLogger.logBeforeMethod(joinPoint);
  }

  @AfterReturning(pointcut = "annotatedMethodPointcut()", returning = "retVal")
  public void doLogAfterMethod(JoinPoint joinPoint, Object retVal) throws Throwable {
    annotationLogger.logAfterMethod(joinPoint, retVal);
  }

  @AfterThrowing(pointcut = "annotatedMethodPointcut()", throwing = "ex")
  public void doLogAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    annotationLogger.logAfterThrowing(joinPoint, ex);
  }

}
