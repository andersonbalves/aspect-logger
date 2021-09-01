package br.com.baratella.logger.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.log.Fields;
import io.opentracing.tag.Tags;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;

@RequiredArgsConstructor
public class LoggerAttributeHandlerOpentracingImpl implements ILoggerAttributeHandler {

  private final Tracer tracer;

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    return true;
  }

  @Override
  public void handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    dto.getExtraParams().put("opentracing", tracer.activeSpan().context());
  }

  @SneakyThrows
  private String convert(Object e) {
    return mapper.writeValueAsString(e);
  }

  @Override
  public void handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
  }

  @Override
  public void handleAfterThrow(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = tracer.scopeManager().activeSpan();
    Tags.ERROR.set(span, Boolean.TRUE);
    Map<String, Object> message = new HashMap();
    Arrays.stream(attributes)
        .filter(e -> e instanceof Throwable)
        .findFirst()
        .ifPresent(e -> message.put(Fields.ERROR_OBJECT, e));
    span.log(message);
  }

  abstract class IgnoreSchemaProperty {

    @JsonIgnore
    abstract void getSchema();

    @JsonIgnore
    abstract void getSpecificData();
  }
}
