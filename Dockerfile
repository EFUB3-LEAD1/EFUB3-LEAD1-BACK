FROM openjdk:11-jdk-slim
COPY build/libs/hanatour-0.0.1-SNAPSHOT.jar hanatour.jar
EXPOSE 8080
ENTRYPOINT exec java -jar hanatour.jar