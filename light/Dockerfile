FROM openjdk:11-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} light.jar
ENTRYPOINT ["java","-jar","/light.jar"]