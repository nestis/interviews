FROM openjdk:alpine
VOLUME /config
ARG JAR_FILE
ADD ${JAR_FILE} config-microservice.jar
ENV JAVA_OPTS ""
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /config-microservice.jar