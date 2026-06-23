FROM maven
WORKDIR /opt/server
EXPOSE 8081
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/auth-service-0.0.1-SNAPSHOT.jar auth.jar
CMD ["java","-jar","auth.jar"]