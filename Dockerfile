# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
# Télécharger les dépendances en premier (optimisation du cache Docker)
RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B -Dproject.build.sourceEncoding=UTF-8

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/pokedex-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]