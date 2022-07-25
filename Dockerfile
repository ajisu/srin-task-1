FROM eclipse-temurin:8u322-b06-jdk-alpine

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8097
ENTRYPOINT ["java","-jar","/app.jar"]