package br.com.baratella.logger.config;

import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.handler.impl.LoggerAttributeHandlerOpentelemetryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AspectLoggerOpentelemetryConfig {

  @Bean
  ILoggerAttributeHandler loggerAttributeHandlerOpentelemetryImpl() {
    return new LoggerAttributeHandlerOpentelemetryImpl();
  }

}
