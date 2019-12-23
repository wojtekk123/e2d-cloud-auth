FROM openjdk:8u201-jdk-alpine3.9
ADD target/e2d_cloudauth-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
ENTRYPOINT java -jar e2d_cloudauth-0.0.1-SNAPSHOT.jar e2d_cloudauth
