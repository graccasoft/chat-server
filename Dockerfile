# Stage 1: Build the application with Gradle
FROM gradle:latest AS builder
WORKDIR /app
COPY . .
RUN gradle clean build --no-daemon

# Stage 2: Create the final Docker image
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/chat-server-0.0.1-SNAPSHOT.jar .
EXPOSE 8082
CMD ["java", "-jar", "chat-server-0.0.1-SNAPSHOT.jar"]
