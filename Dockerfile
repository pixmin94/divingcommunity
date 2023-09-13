FROM node:18-alpine AS builder1

WORKDIR /app

COPY /client .

RUN npm ci
RUN npm i -g @angular/cli
RUN ng build

FROM eclipse-temurin:17 AS builder2

WORKDIR /app

COPY /backend .
COPY --from=builder1 /app/dist/* /app/src/main/resources/static

RUN ./mvnw install -DskipTests

FROM eclipse-temurin:17

COPY --from=builder2 /app/target/*.jar seasquad/app.jar

ENV PORT=80

EXPOSE ${PORT}

ENTRYPOINT SERVER_PORT=${PORT} java -jar /seasquad/app.jar -Dserver.port=${PORT}