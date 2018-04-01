FROM openjdk:alpine
VOLUME /categories
ARG JAR_FILE
ADD ${JAR_FILE} test-microservice.jar
ENV JAVA_OPTS
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /tests-microservice.jar
EXPOSE 8080