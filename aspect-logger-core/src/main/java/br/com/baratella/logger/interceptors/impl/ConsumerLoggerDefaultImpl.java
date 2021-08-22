package br.com.baratella.logger.interceptors.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.interceptors.IConsumerLogger;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
@RequiredArgsConstructor
public class ConsumerLoggerDefaultImpl implements IConsumerLogger {

  private final List<ILoggerAttributeHandler> handlers;

  @Override
  public Object logAroundConsumerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    LoggerDTO dto = new LoggerDTO(joinPoint);
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleBefore(dto, joinPoint));

    log.info("-> Método " + dto.getMethod() + " iniciado com as seguintes informações:\n"
        + dto.toString());

    long startTime = new Date().getTime();
    Object retorno = null;
    try {
      retorno = joinPoint.proceed(joinPoint.getArgs());
    } catch (Throwable t) {
      handlers.stream()
          .filter(e -> e.canHandle(dto, joinPoint))
          .forEach(e -> e.handleAfterThrow(dto, joinPoint, t));
      throw t;
    } finally {
      long endTime = new Date().getTime();
      String stringRetorno = null;
      if (retorno != null) {
        stringRetorno = retorno.toString();
      }

      log.info("<- O método " + dto.getMethod() + " levou " +
          (endTime - startTime) + "ms e retornou:\n" + stringRetorno);
      Object finalRetorno = retorno;
      handlers.stream()
          .filter(e -> e.canHandle(dto, joinPoint))
          .forEach(e -> e.handleAfter(dto, joinPoint, finalRetorno));
    }
    return retorno;
  }
}
