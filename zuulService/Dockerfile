FROM openjdk:latest

ADD build/libs/zuulService-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 5555