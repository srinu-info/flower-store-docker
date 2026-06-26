# FROM maven
# WORKDIR /opt/server
# EXPOSE 8082
# COPY pom.xml .
# COPY src /opt/server/src
# RUN mvn clean package
# RUN mv /opt/server/target/product-service-0.0.1-SNAPSHOT.jar product.jar
# CMD ["java","-jar","product.jar"]

FROM maven:3.9-eclipse-temurin-17  AS builder
WORKDIR /opt/server
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
RUN addgroup -S svdvps && adduser -S srinu -G svdvps
WORKDIR /opt/server
EXPOSE 8082
USER srinu
COPY --from=builder /opt/server/target/product-service-0.0.1-SNAPSHOT.jar product.jar
CMD ["java","-jar","product.jar"]