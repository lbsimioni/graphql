FROM adoptopenjdk:11-jre-hotspot

COPY target/publisher-1.0.0.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]