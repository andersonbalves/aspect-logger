FROM openjdk:11
MAINTAINER andersonbalves
RUN mkdir -p /app/bin
COPY ./target/spring-observability-opentracing-0.0.1.jar /app/bin/spring-observability-opentracing.jar
CMD java -jar /app/bin/spring-observability-opentracing.jar
