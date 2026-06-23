FROM maven
WORKDIR /opt/server
EXPOSE 8084
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/order-service-0.0.1-SNAPSHOT.jar order.jar
CMD ["java","-jar","order.jar"]