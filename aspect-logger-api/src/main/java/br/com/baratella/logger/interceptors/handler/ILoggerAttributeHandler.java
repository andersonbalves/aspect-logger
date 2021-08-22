package br.com.baratella.logger.interceptors.handler;

import br.com.baratella.logger.entity.dto.LoggerDTO;
import org.aspectj.lang.JoinPoint;

public interface ILoggerAttributeHandler {

  boolean canHandle(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);

  LoggerDTO handleBefore(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);

  LoggerDTO handleAfter(LoggerDTO dto, JoinPoint joinPoint, Object... attributes);
}
