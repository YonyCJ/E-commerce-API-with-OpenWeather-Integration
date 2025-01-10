# Usar la imagen oficial de Amazon Corretto 21 como base
FROM amazoncorretto:21-alpine AS builder

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Instalar Maven
RUN apk add --no-cache maven

# Copiar el archivo de construcción
COPY pom.xml ./

# Descargar las dependencias
RUN mvn dependency:go-offline

# Copiar todo el código fuente al contenedor
COPY src /app/src

# Construir el proyecto
RUN mvn package -DskipTests

# Contenedor final para la ejecución
FROM amazoncorretto:21-alpine

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR generado en la construcción desde el contenedor builder
COPY --from=builder /app/target/pt_crud_ecommerce-0.0.1-SNAPSHOT.jar /app/pt_crud_ecommerce-0.0.1-SNAPSHOT.jar

# Exponer el puerto
EXPOSE 8084

# Ejecutar el archivo JAR de la aplicación
ENTRYPOINT ["java", "-jar", "/app/pt_crud_ecommerce-0.0.1-SNAPSHOT.jar"]