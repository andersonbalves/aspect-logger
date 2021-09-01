package br.com.baratella.spring.infra.kafka.config;

import io.opentracing.Tracer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.message.ObjectMessage;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomKafkaListenerErrorHandler implements KafkaListenerErrorHandler {

  private final Tracer tracer;

  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException e) {

    log.error(new ObjectMessage(message).getFormattedMessage(), e);
    return null;
  }
}
