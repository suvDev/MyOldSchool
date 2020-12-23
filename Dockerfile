FROM openjdk:latest

ADD build/libs/manager-0.0.2.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 5000