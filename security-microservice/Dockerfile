FROM openjdk:alpine
VOLUME /security
ARG JAR_FILE
ADD ${JAR_FILE} security-microservice.jar
ENV JAVA_OPTS
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /security-microservice.jar