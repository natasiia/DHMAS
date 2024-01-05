# Use Java 17
FROM openjdk:17-jdk-slim

# Set the working directory in the Docker container
WORKDIR /app

# Copy the JAR file and XML files into the container at /app
COPY target/has-0.0.1-SNAPSHOT-allinone.jar /app/has-0.0.1-SNAPSHOT-allinone.jar

# Expose the port
EXPOSE 8085

# Command to run the application
CMD ["java", "-jar", "/app/has-0.0.1-SNAPSHOT-allinone.jar"]
