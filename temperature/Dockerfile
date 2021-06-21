FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} temperature.jar
ENTRYPOINT ["java","-jar","/temperature.jar"]