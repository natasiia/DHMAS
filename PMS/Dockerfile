# Use Java 17
FROM openjdk:17-jdk-slim

# Set the working directory in the Docker container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/pms-0.0.1-SNAPSHOT.jar /app/pms.jar

# Expose the port
EXPOSE 8083

# Command to run the application
CMD ["java", "-jar", "/app/pms.jar"]
