FROM maven:3.5-jdk-8 as builder
WORKDIR /tests-microservices
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package -DskipTests=true

FROM openjdk:alpine
COPY --from=builder /tests-microservices/target/tests-microservices-0.0.1-SNAPSHOT.jar /tests-microservices.jar
ENV JAVA_OPTS ""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /tests-microservices.jar
