# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="BJomo@safaricom.co.ke"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9001

# The application's jar file
ARG JAR_FILE=target/survey-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} survey.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/survey.jar"]
