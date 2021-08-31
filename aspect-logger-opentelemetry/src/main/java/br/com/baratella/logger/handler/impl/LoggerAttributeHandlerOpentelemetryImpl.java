package br.com.baratella.logger.handler.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.opentelemetry.api.trace.Span;
import java.util.Arrays;
import lombok.SneakyThrows;
import org.aspectj.lang.JoinPoint;

public class LoggerAttributeHandlerOpentelemetryImpl implements ILoggerAttributeHandler {

  private final ObjectMapper mapper = new ObjectMapper();


  public LoggerAttributeHandlerOpentelemetryImpl() {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.addMixIn(Object.class, IgnoreSchemaProperty.class);
  }

  @Override
  public boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    return true;
  }

  @Override
  @SneakyThrows
  public void handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Span span = Span.current();
    span.addEvent(mapper.writeValueAsString(dto));
    dto.getExtraParams().put("opentelemetry", span);
  }

  @Override
  public void handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
  }

  @Override
  public void handleAfterThrow(LoggerDTO dto, JoinPoint joinPoint, Object... attributes) {
    Arrays.stream(attributes)
        .filter(e -> e instanceof Throwable)
        .findFirst()
        .map(e -> (Throwable) e)
        .ifPresent(e -> Span.current().recordException(e));
  }

  abstract class IgnoreSchemaProperty {

    @JsonIgnore
    abstract void getSchema();

    @JsonIgnore
    abstract void getSpecificData();
  }


}
