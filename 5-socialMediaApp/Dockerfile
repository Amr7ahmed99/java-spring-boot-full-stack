FROM openjdk:18-slim-buster
COPY target/*.jar socialMediaApp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/socialMediaApp-0.0.1-SNAPSHOT.jar" ]