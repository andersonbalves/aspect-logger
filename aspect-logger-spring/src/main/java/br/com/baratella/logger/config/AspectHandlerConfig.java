package br.com.baratella.logger.config;

import br.com.baratella.logger.interceptors.handler.impl.LoggerAttributeHandlerDefaultImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AspectHandlerConfig {

  @Bean
  public LoggerAttributeHandlerDefaultImpl loggerAttributeHandlerDefault() {
    return new LoggerAttributeHandlerDefaultImpl();
  }

}
