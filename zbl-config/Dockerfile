# Fetching latest version of Java
FROM openjdk:11

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY zbl-config/target/config-0.0.1-SNAPSHOT.jar /app
# Exposing port 8080
EXPOSE 8888

# Starting the application
CMD ["java", "-jar", "/app/config-0.0.1-SNAPSHOT.jar"]

