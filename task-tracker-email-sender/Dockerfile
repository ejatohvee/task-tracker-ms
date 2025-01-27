FROM openjdk:21-jdk-slim
LABEL authors="maksimignatov"

WORKDIR /app
COPY task-tracker-email-sender/target/task-tracker-email-sender-0.0.1-SNAPSHOT.jar /app/task-tracker-email-sender-0.0.1-SNAPSHOT.jar
CMD ["java", "-XX:+UseG1GC", "-jar", "task-tracker-email-sender-0.0.1-SNAPSHOT.jar"]