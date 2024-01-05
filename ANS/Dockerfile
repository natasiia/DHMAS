# Use Java 17
FROM openjdk:17-jdk-slim

# Set the working directory in the Docker container
WORKDIR /app

# Copy the JAR file and application.properties file into the container at /app
COPY target/ANS-1.0-SNAPSHOT-allinone.jar /app/ANS-1.0-SNAPSHOT-allinone.jar

# Expose the port
EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java","-jar","ANS-1.0-SNAPSHOT-allinone.jar"]
