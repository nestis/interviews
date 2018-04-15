FROM maven:3.5-jdk-8 as builder
COPY ./src /questions-microservices/src
COPY ./pom.xml /questions-microservices
WORKDIR /questions-microservices
RUN mvn clean package -DskipTests=true

FROM openjdk:alpine
VOLUME /questions
COPY --from=builder /questions-microservices/target/questions-microservices-0.0.1-SNAPSHOT.jar /questions-microservices.jar
ENV JAVA_OPTS ""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /questions-microservices.jar
