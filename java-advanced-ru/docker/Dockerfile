FROM gradle:7.4.0-jdk17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} docker.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "docker.jar"]