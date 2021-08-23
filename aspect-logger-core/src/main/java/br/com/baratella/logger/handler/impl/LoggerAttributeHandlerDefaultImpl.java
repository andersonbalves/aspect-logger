package br.com.baratella.logger.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class LoggerAttributeHandlerDefaultImpl implements ILoggerAttributeHandler {

  @Override
  public boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    return true;
  }

  @Override
  public void handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    buildParamsMap(dto, joinPoint);
    if (attributes.length > 0) {
      Arrays.stream(attributes)
          .map(e -> e != null)
          .forEach(e -> dto.getParams().put(e.getClass().getSimpleName(), e));
    }
  }

  @Override
  public void handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    if (attributes.length > 0) {
      Arrays.stream(attributes)
          .map(e -> e != null)
          .forEach(e -> dto.getParams().put(e.getClass().getSimpleName(), e));
    }
  }

  @Override
  public void handleAfterThrow(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
  }

  private void buildParamsMap(LoggerDTO dto, JoinPoint joinPoint) {
    String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    Object[] values = joinPoint.getArgs();
    if (argNames.length != 0) {
      for (int i = 0; i < argNames.length; i++) {
        dto.getArgs().put(argNames[i], values[i]);
      }
    }
  }
}
