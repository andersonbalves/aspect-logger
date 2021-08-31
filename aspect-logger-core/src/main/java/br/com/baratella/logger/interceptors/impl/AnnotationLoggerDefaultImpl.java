package br.com.baratella.logger.interceptors.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.interceptors.IAnnotationLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

@Slf4j
@RequiredArgsConstructor
public class AnnotationLoggerDefaultImpl implements IAnnotationLogger {

  private final List<ILoggerAttributeHandler> handlers;

  private final ObjectMapper mapper;

  @Override
  @SneakyThrows
  public void logBeforeMethod(JoinPoint joinPoint) {
    LoggerDTO dto = new LoggerDTO(joinPoint, "Method called");
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleBefore(dto, joinPoint));
    log.info(mapper.writeValueAsString(dto));
  }

  @Override
  @SneakyThrows
  public void logAfterMethod(JoinPoint joinPoint, Object retVal) {
    LoggerDTO dto = new LoggerDTO(joinPoint, "Method return", retVal);
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleBefore(dto, joinPoint));
    log.info(mapper.writeValueAsString(dto));
  }

  @Override
  @SneakyThrows
  public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    LoggerDTO dto = new LoggerDTO(joinPoint, "Method throw");
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleAfterThrow(dto, joinPoint, ex));
    throw ex;
  }

}
