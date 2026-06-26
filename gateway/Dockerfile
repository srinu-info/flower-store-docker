# FROM maven
# WORKDIR /opt/server
# EXPOSE 8080
# COPY pom.xml .
# COPY src /opt/server/src
# RUN mvn clean package
# RUN mv /opt/server/target/api-gateway-0.0.1-SNAPSHOT.jar gateway.jar
# CMD ["java","-jar","gateway.jar"]

FROM maven:3.9-eclipse-temurin-17  AS builder
WORKDIR /opt/server
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/server
EXPOSE 8081
COPY --from=builder /opt/server/target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar
CMD ["java","-jar","api-gateway.jar"]