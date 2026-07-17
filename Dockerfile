FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copy gradle wrapper and build files
COPY gradlew .
COPY gradlew.bat .
COPY gradle gradle/
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy source code
COPY src src/

# Build the application
RUN chmod +x gradlew && ./gradlew build -x test --no-daemon

# Runtime stage
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Create a non-root user for security
RUN addgroup -g 1000 appuser && adduser -D -u 1000 -G appuser appuser

# Copy the built jar from builder stage
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# Change ownership to non-root user
RUN chown appuser:appuser /app/app.jar

USER appuser

# Expose the port Spring Boot listens on
EXPOSE 80

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]