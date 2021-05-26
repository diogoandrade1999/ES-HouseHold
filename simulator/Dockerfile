FROM openjdk:11-jdk

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} simulator.jar
ENTRYPOINT ["java","-jar","/simulator.jar"]