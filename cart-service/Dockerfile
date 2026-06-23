FROM maven
WORKDIR /opt/server
EXPOSE 8083
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/cart-service-0.0.1-SNAPSHOT.jar cart.jar
CMD ["java","-jar","cart.jar"]