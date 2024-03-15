FROM maven:3.9.6-eclipse-temurin-17

RUN mkdir /app
COPY /pom.xml /app
COPY /src /app/src
RUN cd /app && mvn clean install

ENTRYPOINT ["java", "-jar", "/app/target/fiaptrip-0.0.1-SNAPSHOT.jar"]