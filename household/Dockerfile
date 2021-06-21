FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 51000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} household.jar
ENTRYPOINT ["java","-jar","/household.jar"]
