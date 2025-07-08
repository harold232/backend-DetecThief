# Build stage: compila el microservicio con Maven y Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage final: solo Java, JAR y .env
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Copia el .env que ya está en esta carpeta al contenedor
COPY .env .env

EXPOSE 8080
ENV PORT=8080

CMD ["sh", "-c", "java -jar app.jar --server.port=${PORT}"]
