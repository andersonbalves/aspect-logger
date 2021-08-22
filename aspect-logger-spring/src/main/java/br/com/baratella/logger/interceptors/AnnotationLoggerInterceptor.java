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
public class AnnotationLoggerInterceptor {

  private final IAnnotationLogger annotationLogger;

  @Pointcut("execution(@br.com.baratella.logger.entity.annotation.LogMethod * *(..))")
  private void annotatedMethodPointcut() {
  }

  @Around("annotatedMethodPointcut()")
  public Object doAroundIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
    return annotationLogger.logAroundAnnotatedMethod(joinPoint);
  }

}
