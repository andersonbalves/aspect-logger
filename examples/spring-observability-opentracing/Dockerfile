FROM openjdk:11
MAINTAINER andersonbalves
RUN mkdir -p /app/bin
COPY ./target/spring-observability-opentracing-0.0.1.jar /app/bin/spring-observability-opentracing.jar
CMD mvn install
ENV BOOTSRAP_SERVERS=kafka:9092
ENV SCHEMA_REGISTRY_URL=http://schema-registry:8081
ENV JAEGER_HOST=jaeger
ENV JAEGER_PORT=6831
CMD java -jar /app/bin/spring-observability-opentracing.jar
