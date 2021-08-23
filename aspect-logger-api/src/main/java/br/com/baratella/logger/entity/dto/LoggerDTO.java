package br.com.baratella.logger.entity.dto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

@Data
public class LoggerDTO {

  private final Map<String, Object> args = new HashMap<>();
  private final Map<String, Object> params = new HashMap<>();
  private String className;
  private String method;

  public LoggerDTO(JoinPoint joinPoint) {
    this.setClassName(joinPoint.getTarget().getClass().getName());
    this.setMethod(getCompleteMethodSignature(joinPoint));
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
