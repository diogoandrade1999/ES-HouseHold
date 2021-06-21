FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} humidity.jar
ENTRYPOINT ["java","-jar","/humidity.jar"]