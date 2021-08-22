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
public class ControllerLoggerInterceptor {

  private final IControllerLogger controllerLogger;

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  private void restControllerPointcut() {
  }

  @Around("restControllerPointcut()")
  public Object doAroundIntercept(ProceedingJoinPoint joinPoint) throws Throwable {
    return controllerLogger.logAroundControllerMethod(joinPoint);
  }

}
