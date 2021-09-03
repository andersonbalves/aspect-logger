# aspect-logger

The goal of this project is provide some libs to generate logs based on Aspect Oriented Programming

## Libraries

- **aspect-logger-api** - Provides a set classes and interfaces
- **aspect-logger-core** - Provides the library core implementation such as:
    - *AnnotationLogger* - Logger to `@LogMethod` annotated methods
    - *ControllerLogger* - Logger to Controller objects
    - *ConsumerLogger* - Logger to KafkaConsumer objects
    - *ProducerLogger* - Logger to KafkaProducer objects
- **aspect-logger-spring** - Enable AOP Interception based on Spring AOP
- **aspect-logger-opentelemetry** - Provides a handler to add logs to Opentelemetry distributed
  tracing
- **aspect-logger-opentelemetry-spring** - Enable aspect-logger-opentelemetry Beans
- **aspect-logger-opentracing** - Provides a handler to add logs to Opentracing distributed tracing
- **aspect-logger-opentracing-spring** - Enable aspect-logger-opentracing Beans

## Usage

### Spring

If you want to add auto logs to your application then add the following dependency to your pom:

```xml

<dependency>
  <artifactId>aspect-logger-spring</artifactId>
  <groupId>br.com.baratella.logger.aspect</groupId>
  <version>${aspect.logger.version}</version>
</dependency>
```

Then enable ComponentScan to aspect-logger in your application:

```java

@Configuration
@ComponentScan("br.com.baratella.logger")
public class AOPLoggerConfiguration {

}
```

#### Enable Opentelemetry logs

To enable Opentelemetry logs you must add this dependency:

```xml

<dependency>
  <artifactId>aspect-logger-opentelemetry-spring</artifactId>
  <groupId>br.com.baratella.logger.aspect</groupId>
  <version>${aspect.logger.version}</version>
</dependency>
```

#### Enable Opentracing logs

To enable Openttracing logs you must add this dependency:

```xml

<dependency>
  <artifactId>aspect-logger-opentracing-spring</artifactId>
  <groupId>br.com.baratella.logger.aspect</groupId>
  <version>${aspect.logger.version}</version>
</dependency>
```

## Example of usage

You can find examples of
usage [`clicking here`](https://github.com/andersonbalves/aspect-logger/tree/main/examples) 
