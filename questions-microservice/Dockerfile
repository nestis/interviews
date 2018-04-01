FROM openjdk:alpine
VOLUME /categories
ARG JAR_FILE
ADD ${JAR_FILE} questions-microservice.jar
ENV JAVA_OPTS
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /questions-microservice.jar
EXPOSE 8080