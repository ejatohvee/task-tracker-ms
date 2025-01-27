FROM openjdk:21-jdk-slim
LABEL authors="maksimignatov"

WORKDIR /app
COPY task-tracker-api/target/task-tracker-api-0.0.1-SNAPSHOT.jar /app/task-tracker-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080/tcp
CMD ["java", "-XX:+UseG1GC", "-jar", "task-tracker-api-0.0.1-SNAPSHOT.jar"]