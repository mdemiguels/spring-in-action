# Usar una imagen base de Java
FROM eclipse-temurin:21-jre

# Copiar el archivo JAR de tu aplicación al contenedor
COPY target/taco-cloud*.jar app.jar

# Exponer el puerto en el que tu aplicación se ejecutará
EXPOSE 8080

# Definir el comando para ejecutar tu aplicación
ENTRYPOINT ["java","-jar","/app.jar"]