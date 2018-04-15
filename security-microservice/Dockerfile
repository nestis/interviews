FROM maven:3.5-jdk-8 as builder
WORKDIR /security-service
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

FROM openjdk:alpine
VOLUME /security
COPY --from=builder /security-service/target/security-service-0.0.1-SNAPSHOT.jar /security-service.jar
ENV JAVA_OPTS ""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /security-service.jar
