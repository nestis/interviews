FROM openjdk:alpine
VOLUME /categories
ARG JAR_FILE
ADD ${JAR_FILE} categories-microservice.jar
ENV JAVA_OPTS -Dspring.data.mongodb.host=192.168.1.252
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -jar /categories-microservice.jar
EXPOSE 8080