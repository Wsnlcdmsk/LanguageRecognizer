# Stage 1: Build the application
FROM maven:3.9.2-eclipse-temurin-17-alpine as builder

WORKDIR /app

COPY ./pom.xml ./pom.xml
COPY ./src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
