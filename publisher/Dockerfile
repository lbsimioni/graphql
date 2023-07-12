# Define a imagem base
FROM adoptopenjdk:11-jre-hotspot

# Copia o arquivo JAR da aplicação para dentro do container
COPY target/publisher-1.0.0.jar /app/app.jar

# Define o diretório de trabalho como /app
WORKDIR /app

# Expõe a porta em que a aplicação estará rodando
EXPOSE 8080

# Comando para executar a aplicação quando o container for iniciado
CMD ["java", "-jar", "app.jar"]