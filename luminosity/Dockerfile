FROM openjdk:11-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} luminosity.jar
ENTRYPOINT ["java","-jar","/luminosity.jar"]