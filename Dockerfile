# Use a base Java image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Set working directory
WORKDIR /app

# Add jar file to container
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
