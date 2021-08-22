package br.com.baratella.logger.interceptors.impl;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import br.com.baratella.logger.interceptors.IServiceLogger;
import br.com.baratella.logger.interceptors.handler.ILoggerAttributeHandler;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;

@RequiredArgsConstructor
public class ServiceLoggerDefaultImpl implements IServiceLogger {

  private final List<ILoggerAttributeHandler> handlers;

  @Override
  public Object logAroundServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    LoggerDTO dto = new LoggerDTO(joinPoint);
    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleBefore(dto, joinPoint));

//    log.info("-> Método " + dto.getMethod() + " iniciado com as seguintes informações:\n"
//        + dto.toString());

    long startTime = new Date().getTime();
    Object result = joinPoint.proceed(joinPoint.getArgs());
    long endTime = new Date().getTime();

    handlers.stream()
        .filter(e -> e.canHandle(dto, joinPoint))
        .forEach(e -> e.handleAfter(dto, joinPoint));

//    log.info("<- O método " + dto.getMethod() + " levou " +
//        (endTime - startTime) + "ms e retornou:\n" + result.toString());
    return result;
  }
}
