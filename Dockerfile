FROM openjdk:8-jdk-alpine

# Set working directory
WORKDIR /app

# Copy maven files to app directory
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Copy source to app directory
COPY src src

# Build app
RUN ./mvnw install -DskipTests -q

# Run app
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "target/project-management-service-1.0.0.jar"]