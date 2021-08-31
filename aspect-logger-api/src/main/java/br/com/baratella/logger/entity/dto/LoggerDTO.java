package br.com.baratella.logger.entity.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@Data
public class LoggerDTO {

  private final String eventName;
  private final String className;
  private final String methodSignature;
  private final Map<String, Object> inputArgs;
  private final Object returnValue;
  private final Map<String, Object> extraParams;

  public LoggerDTO(JoinPoint joinPoint, String eventName, Object returnValue) {
    this.eventName = eventName;
    this.className = joinPoint.getTarget().getClass().getName();
    this.methodSignature = getCompleteMethodSignature(joinPoint);
    this.inputArgs = new HashMap<>();
    this.extraParams = new HashMap<>();
    this.returnValue = returnValue;
  }

  public LoggerDTO(JoinPoint joinPoint, String eventName) {
    this(joinPoint, eventName, null);
  }

  private String getCompleteMethodSignature(JoinPoint joinPoint) {
    String methodSignature =
        joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "(";
    List<String> argTypes = Arrays
        .stream(((MethodSignature) joinPoint.getSignature()).getParameterTypes())
        .map(param -> {
          String[] type = param.getCanonicalName().split("\\.");
          return type[type.length - 1];
        })
        .collect(Collectors.toList());
    String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    if (argTypes.size() > 0) {
      for (int i = 0; i < argTypes.size(); i++) {
        methodSignature = methodSignature + argTypes.get(i) + " " + argNames[i];
        if (i < argTypes.size() - 1) {
          methodSignature = methodSignature + ", ";
        }
      }
    }
    methodSignature = methodSignature + ")";
    return methodSignature;
  }

}
