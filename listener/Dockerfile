FROM adoptopenjdk:11-jre-hotspot

COPY target/listener-1.0.0.jar /app/app.jar

WORKDIR /app

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]