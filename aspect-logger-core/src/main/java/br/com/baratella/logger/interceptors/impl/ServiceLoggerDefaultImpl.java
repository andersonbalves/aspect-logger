package br.com.baratella.logger.interceptors.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.interceptors.IServiceLogger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

@Slf4j
@RequiredArgsConstructor
public class ServiceLoggerDefaultImpl implements IServiceLogger {

  private final List<ILoggerAttributeHandler> handlers;

  @Override
  public void logBeforeMethod(JoinPoint joinPoint) {
    LoggerDTO dto = new LoggerDTO(joinPoint);
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleBefore(dto, joinPoint));

    log.info("-> Método " + dto.getMethod() + " iniciado com as seguintes informações:\n"
        + dto.toString());
  }

  @Override
  public void logAfterMethod(JoinPoint joinPoint, Object retVal) {
    LoggerDTO dto = new LoggerDTO(joinPoint);
  }

  @Override
  public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
    LoggerDTO dto = new LoggerDTO(joinPoint);
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleAfterThrow(dto, joinPoint, ex));
    throw ex;
  }
}
