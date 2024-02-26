# Dockerfile for Spring Boot applications
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY . /app
COPY --from=commons /root/.m2 /root/.m2
RUN mvn clean install -DskipTests

FROM maven:3.8.4-openjdk-17-slim
WORKDIR /app
COPY --from=build /app/target/*.jar /app/service.jar
ENTRYPOINT ["java","-jar","/app/service.jar"]