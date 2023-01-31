# # Fetching latest version of Java
# FROM openjdk:11

# # Setting up work directory
# WORKDIR /app

# # Copy the jar file into our app
# COPY zbl-config/target/config-0.0.1-SNAPSHOT.jar /app
# # Exposing port 8080
# EXPOSE 8888

# # Starting the application
# CMD ["java", "-jar", "/app/config-0.0.1-SNAPSHOT.jar"]

##### newer version
FROM maven:3.8.4-openjdk-17 as maven-builder
COPY zbl-config/src /app/src
COPY zbl-config/pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:11

COPY --from=maven-builder app/target/config-0.0.1-SNAPSHOT.jar /app-service/config-0.0.1-SNAPSHOT.jar
WORKDIR /app-service

EXPOSE 8888
ENTRYPOINT ["java","-jar","config-0.0.1-SNAPSHOT.jar"]

