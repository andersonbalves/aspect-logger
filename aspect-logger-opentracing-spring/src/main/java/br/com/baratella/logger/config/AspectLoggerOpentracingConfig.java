package br.com.baratella.logger.config;

import br.com.baratella.logger.interceptors.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.interceptors.handler.impl.LoggerAttributeHandlerOpentracingImpl;
import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AspectLoggerOpentracingConfig {

  private final Tracer tracer;

  @Bean
  ILoggerAttributeHandler loggerAttributeHandlerOpentracing() {
    return new LoggerAttributeHandlerOpentracingImpl(tracer);
  }

}
