FROM openjdk:17-jdk as builder
COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=Asia/Seoul", "-jar", "/app.jar"]
