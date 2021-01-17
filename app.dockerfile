FROM openjdk:11-jdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
EXPOSE 8046
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]