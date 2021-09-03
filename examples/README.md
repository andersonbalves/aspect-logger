# Spring AOP Logger with Jaeger integration
The goal of this project is to show the usage of aspect-logger integrating with [`Spring Boot`](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/) and [`Spring Kafka`](https://docs.spring.io/spring-kafka/reference/html/).
In this project you can also find examples of integration between Spring Boot Applications with [`Opentelemetry`](https://opentelemetry.io/) and [`Opentracing`](https://opentracing.io/).

## Prerequisites

- [`Java 11+`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [`Docker`](https://www.docker.com/)
- [`Docker-Compose`](https://docs.docker.com/compose/install/)
- [`NodeJS`](https://nodejs.org/en/)

##Running examples

###Build Java projects
To build java applications, go to a terminal and, inside root folder, run the command below
```sh
mvn clean install
```
###Build Angular Project
To build the Angular project, inside `angular-observability` root folder, run:
```sh
npm install
```
Then run:
```sh
npm run build --prod
```

###Build and Run Docker images
To build docker and run Docker images, inside root folder run the command:
```sh
docker-compose up --build
```

###Shutdown
To stop and remove docker-compose containers, network and volumes, go to a terminal and, inside root folder, run the command below
```sh
docker-compose down
```

## Useful Links
###Frontend Angular
  Frontend can be accessed at http://localhost:4200
###Jaeger UI
  `Jaeger UI` can be accessed at http://localhost:16686/search