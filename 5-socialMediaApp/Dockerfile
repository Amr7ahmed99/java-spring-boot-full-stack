FROM maven:3.8.6-openjdk-18-slim AS build
WORKDIR /home/socialMediaApp

COPY ./pom.xml /home/socialMediaApp/pom.xml
COPY ./src/main/java/restfulWebService/socialMediaApp/SocialMediaAppApplication.java /home/socialMediaApp/src/main/java/restfulWebService/socialMediaApp/SocialMediaAppApplication.java

RUN mvn -f /home/socialMediaApp/pom.xml clean package

COPY . /home/socialMediaApp/
RUN mvn -f /home/socialMediaApp/pom.xml clean package


FROM openjdk:18-slim-buster
COPY --from=build /home/socialMediaApp/target/*.jar socialMediaApp-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar /socialMediaApp-0.0.1-SNAPSHOT.jar" ]