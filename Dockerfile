# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests -B -Dproject.build.sourceEncoding=UTF-8

FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
WORKDIR /app

ARG JAR_FILE=target/pokedex-0.0.1-SNAPSHOT.jar
COPY --from=build /app/${JAR_FILE} /app/app.jar
RUN chown appuser:appgroup /app/app.jar

USER appuser
EXPOSE 8080

ENV JAVA_OPTS="-Xms128m -Xmx512m"

HEALTHCHECK --interval=30s --timeout=5s --start-period=10s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["sh","-c","exec java $JAVA_OPTS -jar /app/app.jar"]
