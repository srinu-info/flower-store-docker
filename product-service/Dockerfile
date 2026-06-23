FROM maven
WORKDIR /opt/server
EXPOSE 8082
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/product-service-0.0.1-SNAPSHOT.jar product.jar
CMD ["java","-jar","product.jar"]