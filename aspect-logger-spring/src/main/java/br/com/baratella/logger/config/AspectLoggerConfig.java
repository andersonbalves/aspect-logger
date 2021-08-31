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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AspectLoggerConfig {

  private final List<ILoggerAttributeHandler> handlers;
  private final ObjectMapper mapper = new ObjectMapper();

  @PostConstruct
  public void init() {
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    mapper.addMixIn(Object.class, IgnoreSchemaProperty.class);
  }


  @Bean
  public IAnnotationLogger annotationLoggerDefaultImpl() {
    return new AnnotationLoggerDefaultImpl(handlers, mapper);
  }

  @Bean
  public IConsumerLogger consumerLoggerDefaultImpl() {
    return new ConsumerLoggerDefaultImpl(handlers, mapper);
  }

  @Bean
  public IControllerLogger controllerLoggerDefaultImpl() {
    return new ControllerLoggerDefaultImpl(handlers, mapper);
  }

  @Bean
  public IProducerLogger producerLoggerDefaultImpl() {
    return new ProducerLoggerDefaultImpl(handlers, mapper);
  }

  @Bean
  public IServiceLogger serviceLoggerDefaultImpl() {
    return new ServiceLoggerDefaultImpl(handlers, mapper);
  }

  abstract class IgnoreSchemaProperty {

    @JsonIgnore
    abstract void getSchema();

    @JsonIgnore
    abstract void getSpecificData();
  }

}
