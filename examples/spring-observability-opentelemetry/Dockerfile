FROM openjdk:11
MAINTAINER andersonbalves
RUN mkdir -p /app/bin
COPY ./opentelemetry-javaagent-all.jar /app/bin
COPY ./target/spring-observability-opentelemetry-0.0.1.jar /app/bin/spring-observability-opentelemetry.jar
CMD java -Dotel.traces.exporter=jaeger \
    -Dotel.exporter.jaeger.endpoint=http://jaeger:14250 \
    -Dotel.service.name=spring-observability-opentelemetry \
    -Dotel.propagators=tracecontext,baggage,jaeger \
    -Dotel.metrics.exporter=none \
    -javaagent:/app/bin/opentelemetry-javaagent-all.jar \
    -jar  /app/bin/spring-observability-opentelemetry.jar
