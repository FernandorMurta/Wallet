FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
ARG SPRING_DATASOURCE_URL=jdbc:mysql://localhos:3306/WALLET?useTimezone=true&serverTimezone=UTC
ARG PRING_DATASOURCE_USERNAME=root
ARG SPRING_DATASOURCE_PASSWORD=root
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
