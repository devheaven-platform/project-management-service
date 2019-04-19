FROM openjdk:8-jdk-alpine

# Set working directory
WORKDIR /app

# Copy maven files to app directory
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Copy source to app directory
COPY src src

# Build app
RUN ./gradlew bootJar -q

# Run app
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "build/libs/project-management-service-1.0.0.jar"]