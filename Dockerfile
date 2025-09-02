FROM openjdk:17-jdk-slim-buster
WORKDIR /app
COPY /target/Project2Boot-0.0.1-SNAPSHOT.jar /app/springbootproject.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "springbootproject.jar"]