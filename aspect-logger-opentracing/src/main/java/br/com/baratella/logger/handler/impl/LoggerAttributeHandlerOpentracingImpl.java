package br.com.baratella.logger.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.log.Fields;
import io.opentracing.tag.Tags;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    span.setOperationName(getSpanName(joinPoint));
    dto.getParams().put("opentracing", tracer);
    return dto;
  }

  @Override
  public LoggerDTO handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = tracer.activeSpan();
    span.finish();
    return dto;
  }

  @Override
  public LoggerDTO handleAfterThrow(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = tracer.activeSpan();
    Tags.ERROR.set(span, Boolean.TRUE);
    Map<String, Object> message = new HashMap();
    Arrays.stream(attributes)
        .filter(e -> e instanceof Throwable)
        .findFirst()
        .ifPresent(e -> message.put(Fields.ERROR_OBJECT, e));
    span.log(message);
    return null;
  }

  private String getSpanName(JoinPoint joinPoint) {
    return joinPoint.getTarget().getClass().getSimpleName() + "_" + joinPoint.getSignature().getName();
  }
}
