FROM java:8-jdk-alpine
COPY build/libs/*.jar /opt/apps/challenge-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/apps/challenge-0.0.1-SNAPSHOT.jar"]