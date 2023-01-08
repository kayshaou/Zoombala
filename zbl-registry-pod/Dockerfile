# # Fetching latest version of Java
# FROM openjdk:11

# # Setting up work directory
# WORKDIR /app

# # Copy the jar file into our app
# COPY zbl-registry-pod/target/health-0.0.1-SNAPSHOT.jar /app
# # Exposing port 8080
# EXPOSE 8887

# # Starting the application
# CMD ["java", "-jar", "health-0.0.1-SNAPSHOT.jar"]


##### newer version
FROM maven:3.8.4-openjdk-17 as maven-builder
COPY zbl-registry-pod/src /app/src
COPY zbl-registry-pod/pom.xml /app

RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17

COPY --from=maven-builder app/target/health-0.0.1-SNAPSHOT.jar /app-service/health-0.0.1-SNAPSHOT.jar
WORKDIR /app-service

EXPOSE 8888
ENTRYPOINT ["java","-jar","health-0.0.1-SNAPSHOT.jar"]
