# Backend Dockerfile
FROM openjdk:21 AS build

WORKDIR /app

COPY app .

RUN ./mvnw clean install -DskipTests

EXPOSE 3000

CMD ["java", "-jar", "target/back-0.0.1-SNAPSHOT.jar"]




