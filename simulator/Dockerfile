FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} simulator.jar
ENTRYPOINT ["java","-jar","/simulator.jar"]