FROM openjdk:21-jdk-slim
LABEL authors="maksimignatov"

WORKDIR /app
COPY task-tracker-scheduler/target/task-tracker-scheduler-0.0.1-SNAPSHOT.jar /app/task-tracker-scheduler-0.0.1-SNAPSHOT.jar
EXPOSE 8082/tcp
CMD ["java", "-XX:+UseG1GC", "-jar", "task-tracker-scheduler-0.0.1-SNAPSHOT.jar"]