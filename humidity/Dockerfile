FROM openjdk:11-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} humidity.jar
ENTRYPOINT ["java","-jar","/humidity.jar"]