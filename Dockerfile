FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/franquicias-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]