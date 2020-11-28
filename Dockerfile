# FROM openjdk:11.0.8-jre-slim-buster as builder
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} application.jar
# RUN java -Djarmode=layertools -jar application.jar extract
# FROM openjdk:11.0.8-jre-slim-buster
# WORKDIR application
# COPY application/dependencies/ ./
# COPY application/spring-boot-loader/ ./
# COPY application/snapshot-dependencies/ ./
# COPY application/resources/ ./
# COPY application/application/ ./
# ENTRYPOINT [ "sh", "-c", "java -Xms128m -Xmx256m -jar /app.jar" ]

#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11.0.8-jre-slim-buster
RUN mvn clean package -DskipTests
ADD target/tcc-recipe-service-*.jar tcc-recipe-service.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -Xms128m -Xmx256m -jar /tcc-recipe-service.jar" ]



# ENV DATABASE_HOST=jdbc:postgresql://tcc.ckbc5tcnnws2.sa-east-1.rds.amazonaws.com:5432/tcc
# ENV DATABASE_USERNAME=postgres
# ENV DATABASE_PASSWORD=C51CD2FBB6C6E108439EE6F9015BB95395F8DC89D6546960BBD59D4C71FEA523

# ENTRYPOINT ["java","-jar","/app.jar"]
