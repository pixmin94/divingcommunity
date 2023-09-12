FROM node:18 AS angbuilder

WORKDIR /app

COPY client/src src
COPY client/angular.json .
COPY client/package-lock.json . 
COPY client/package.json . 
COPY client/tsconfig.app.json . 
COPY client/tsconfig.json .
COPY client/tsconfig.spec.json . 

RUN npm i -g @angular/cli 
RUN npm ci 
RUN ng build 

FROM maven:3-eclipse-temurin-17 AS javabuilder

WORKDIR /app

COPY backend/src src
COPY backend/mvnw . 
COPY backend/mvnw.cmd .
COPY backend/pom.xml .
COPY --from=angbuilder /app/dist/client/ /app/src/main/resources/static/

RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=javabuilder /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=123
ENV SPRING_DATASOURCE_USERNAME=fred
ENV SPRING_DATASOURCE_PASSWORD=123

ENV PORT=3000

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar app.jar