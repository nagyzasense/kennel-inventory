FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY build/libs/kennel-inventory-0.0.1-SNAPSHOT.jar /app/kennel-inventory-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/kennel-inventory-0.0.1-SNAPSHOT.jar"]