FROM maven:3.8.4-jdk-8 AS build

COPY src /app/source
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install

COPY --from=build /app/target/auth-backend-0.0.1-SNAPSHOT.jar /app/app.jar

FROM openjdk:8-jre-alpine