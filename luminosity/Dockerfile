FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} luminosity.jar
ENTRYPOINT ["java","-jar","/luminosity.jar"]