package br.com.baratella.logger.interceptors.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.interceptors.handler.ILoggerAttributeHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

public class LoggerAttributeHandlerDefaultImpl implements ILoggerAttributeHandler {

  @Override
  public boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    return true;
  }

  @Override
  public LoggerDTO handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    buildParamsMap(dto, joinPoint);
    if (attributes.length > 0) {
      Arrays.stream(attributes)
          .forEach(e -> dto.getParams().put(e.getClass().getSimpleName(), e));
    }
    return dto;
  }

  @Override
  public LoggerDTO handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    if (attributes.length > 0) {
      Arrays.stream(attributes)
          .forEach(e -> dto.getParams().put(e.getClass().getSimpleName(), e));
    }
    return dto;
  }

  private Map<String, Object> buildParamsMap(LoggerDTO dto, JoinPoint joinPoint) {
    String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    Object[] values = joinPoint.getArgs();
    Map<String, Object> params = new HashMap<>();
    if (argNames.length != 0) {
      for (int i = 0; i < argNames.length; i++) {
        params.put(argNames[i], values[i]);
      }
    }
    return params;
  }
}
