FROM openjdk:11-jdk
EXPOSE 51000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} household.jar
ENTRYPOINT ["java","-jar","/household.jar"]
