# Use official OpenJDK 17 runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Make mvnw executable (Linux)
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build the project
RUN ./mvnw package -DskipTests

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/foine-0.0.1-SNAPSHOT.jar"]
