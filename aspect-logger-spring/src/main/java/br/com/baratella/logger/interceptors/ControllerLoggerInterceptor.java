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
public class ControllerLoggerInterceptor {

  private final IControllerLogger controllerLogger;

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  private void restControllerPointcut() {
  }

  @Before("restControllerPointcut()")
  public void doLogBeforeMethod(JoinPoint joinPoint) throws Throwable {
    controllerLogger.logBeforeMethod(joinPoint);
  }

  @AfterReturning(pointcut = "restControllerPointcut()", returning = "retVal")
  public void doLogAfterMethod(JoinPoint joinPoint, Object retVal) throws Throwable {
    controllerLogger.logAfterMethod(joinPoint, retVal);
  }

  @AfterThrowing(pointcut = "restControllerPointcut()", throwing = "ex")
  public void doLogAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    controllerLogger.logAfterThrowing(joinPoint, ex);
  }

}
