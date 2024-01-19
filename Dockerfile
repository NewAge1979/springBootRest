FROM openjdk:17-jdk-alpine

EXPOSE 8080

COPY build/libs/springBootRest-0.0.1.jar myapp.jar

CMD ["java", "-jar", "myapp.jar"]