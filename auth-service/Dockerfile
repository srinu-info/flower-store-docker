# FROM maven
# WORKDIR /opt/server
# EXPOSE 8081
# COPY pom.xml .
# COPY src /opt/server/src
# RUN mvn clean package
# RUN mv /opt/server/target/auth-service-0.0.1-SNAPSHOT.jar auth.jar
# CMD ["java","-jar","auth.jar"]

FROM maven:3.9-eclipse-temurin-17  AS builder
WORKDIR /opt/server
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /opt/server
EXPOSE 8081
COPY --from=builder /opt/server/target/auth-service-0.0.1-SNAPSHOT.jar auth.jar
CMD ["java","-jar","auth.jar"]