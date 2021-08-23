package br.com.baratella.logger.handler;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import org.aspectj.lang.JoinPoint;

public interface ILoggerAttributeHandler {

  boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);

  void handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);

  void handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);

  void handleAfterThrow(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);
}
