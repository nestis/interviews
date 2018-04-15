FROM maven:3.5-jdk-8 as builder
WORKDIR /config-server
COPY ./src ./src
COPY ./pom.xml .
RUN mvn clean package

FROM openjdk:alpine
COPY --from=builder /config-server/target/config-server-0.0.1-SNAPSHOT.jar /config-server.jar
ENV JAVA_OPTS ""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /config-server.jar
