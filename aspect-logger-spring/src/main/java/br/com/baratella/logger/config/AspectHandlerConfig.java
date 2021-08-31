package br.com.baratella.logger.config;

import br.com.baratella.logger.handler.impl.LoggerAttributeHandlerDefaultImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@RequiredArgsConstructor
public class AspectHandlerConfig {

  @Bean
  @Order(value = 1)
  public LoggerAttributeHandlerDefaultImpl loggerAttributeHandlerDefault() {
    return new LoggerAttributeHandlerDefaultImpl();
  }

}
