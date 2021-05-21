FROM openjdk:8-jdk-alpine

WORKDIR /app
COPY  ./target/weather-forecast-0.0.1-SNAPSHOT.jar /app/weather-forecast.jar
ENTRYPOINT ["java","-jar","/app/weather-forecast.jar"]
EXPOSE 9090