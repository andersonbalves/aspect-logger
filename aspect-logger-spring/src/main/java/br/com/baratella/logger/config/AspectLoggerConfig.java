package br.com.baratella.logger.config;

import br.com.baratella.logger.handler.ILoggerAttributeHandler;
import br.com.baratella.logger.interceptors.IAnnotationLogger;
import br.com.baratella.logger.interceptors.IConsumerLogger;
import br.com.baratella.logger.interceptors.IControllerLogger;
import br.com.baratella.logger.interceptors.IProducerLogger;
import br.com.baratella.logger.interceptors.IServiceLogger;
import br.com.baratella.logger.interceptors.impl.AnnotationLoggerDefaultImpl;
import br.com.baratella.logger.interceptors.impl.ConsumerLoggerDefaultImpl;
import br.com.baratella.logger.interceptors.impl.ControllerLoggerDefaultImpl;
import br.com.baratella.logger.interceptors.impl.ProducerLoggerDefaultImpl;
import br.com.baratella.logger.interceptors.impl.ServiceLoggerDefaultImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AspectLoggerConfig {

  private final List<ILoggerAttributeHandler> handlers;

  @Bean
  public IAnnotationLogger annotationLoggerDefaultImpl() {
    return new AnnotationLoggerDefaultImpl(handlers);
  }

  @Bean
  public IConsumerLogger consumerLoggerDefaultImpl() {
    return new ConsumerLoggerDefaultImpl(handlers);
  }

  @Bean
  public IControllerLogger controllerLoggerDefaultImpl() {
    return new ControllerLoggerDefaultImpl(handlers);
  }

  @Bean
  public IProducerLogger producerLoggerDefaultImpl() {
    return new ProducerLoggerDefaultImpl(handlers);
  }

  @Bean
  public IServiceLogger serviceLoggerDefaultImpl() {
    return new ServiceLoggerDefaultImpl(handlers);
  }

}
