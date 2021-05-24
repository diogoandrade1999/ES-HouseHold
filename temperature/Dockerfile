FROM openjdk:11-jdk
EXPOSE 8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} temperature.jar
ENTRYPOINT ["java","-jar","/temperature.jar"]