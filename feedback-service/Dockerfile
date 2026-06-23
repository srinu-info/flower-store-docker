FROM maven
WORKDIR /opt/server
EXPOSE 8085
COPY pom.xml .
COPY src /opt/server/src
RUN mvn clean package
RUN mv /opt/server/target/feedback-service-0.0.1-SNAPSHOT.jar feedback.jar
CMD ["java","-jar","feedback.jar"]