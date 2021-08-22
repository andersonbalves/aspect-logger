package br.com.baratella.logger.interceptors.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.interceptors.handler.ILoggerAttributeHandler;
import io.opentracing.Span;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;

@RequiredArgsConstructor
public class LoggerAttributeHandlerOpentracingImpl implements ILoggerAttributeHandler {

  private final Tracer tracer;

  @Override
  public boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    return true;
  }

  @Override
  public LoggerDTO handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = tracer.buildSpan(
        getSpanName(joinPoint)).start();
    dto.getParams().put("opentracing", tracer);
    return dto;
  }

  @Override
  public LoggerDTO handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = tracer.activeSpan();
    span.finish();
    return dto;
  }

  private String getSpanName(JoinPoint joinPoint) {
    return joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature();
  }
}
