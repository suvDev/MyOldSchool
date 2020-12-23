FROM openjdk:latest

ADD build/libs/testService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 4445