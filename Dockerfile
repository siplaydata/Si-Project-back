FROM openjdk:11

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY src src/
RUN ./gradlew build

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/backend-0.0.1-SNAPSHOT.jar"]