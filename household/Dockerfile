FROM openjdk:8-jdk-alpine
MAINTAINER josefrias
VOLUME /tmp
EXPOSE 51888
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} householdapp.jar
ENTRYPOINT ["java","-jar","/householdapp.jar"]
