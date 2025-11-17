# Build Stage: Use a full JDK to compile/package
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Production Stage: Use a lightweight JRE
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.placement-webapp-1.0.0.jar

# Configure JVM for low memory environment (CRITICAL for 256MB free tier)
# We limit the max heap size to prevent OOM errors
ENV JAVA_OPTS="-Xmx150m"

# Start the Spring Boot application
CMD ["java", "$JAVA_OPTS", "-jar", "app.jar"]

# Note: Fly.io routes external traffic to the port defined in fly.toml, typically 8080.
EXPOSE 8080