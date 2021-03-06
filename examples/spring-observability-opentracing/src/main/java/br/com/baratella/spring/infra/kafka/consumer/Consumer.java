package br.com.baratella.spring.infra.kafka.consumer;

import br.com.baratella.spring.kafka.avro.User;
import br.com.baratella.spring.usecase.xpto.IXPTOUsecase;
import br.com.baratella.spring.usecase.xpto.XPTOUserDTORequest;
import br.com.baratella.spring.usecase.xpto.XPTOUserDTOResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@RequiredArgsConstructor
@KafkaListener(
    topics = "users",
    groupId = "group_id_opentracing_1",
    errorHandler = "customKafkaListenerErrorHandler")
public class Consumer {

  private final IXPTOUsecase xptoUsecase;

  @KafkaHandler(isDefault = true)
  public void consume(User record) throws InterruptedException {
    XPTOUserDTOResponse response = xptoUsecase.execute(XPTOUserDTORequest.builder()
        .name(record.getName())
        .age(record.getAge())
        .build());
  }
}