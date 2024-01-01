# Use OpenJDK 20 for the runtime base image
FROM openjdk:20-jdk

# Set the working directory in the Docker image
WORKDIR /pdcs-app

# Copy the standalone JAR built with all dependencies
COPY /target/pdcs-0.0.1-allinone.jar ./pdcs.jar

# Command to run the application
CMD ["java", "-jar", "pdcs.jar"]