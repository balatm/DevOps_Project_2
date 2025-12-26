FROM eclipse-temurin:21
WORKDIR /app
COPY target/*.jar app.jar
VOLUME /tmp
EXPOSE 4200
ENTRYPOINT ["java","-jar","app.jar"]