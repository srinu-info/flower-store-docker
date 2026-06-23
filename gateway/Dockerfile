FROM maven
WORKDIR /opt/server
EXPOSE 8080
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/api-gateway-0.0.1-SNAPSHOT.jar gateway.jar
CMD ["java","-jar","gateway.jar"]