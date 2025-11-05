# Use OpenJDK 21 (Alpine for small image size)
FROM eclipse-temurin:21-jdk-alpine

# Set working directory inside container
WORKDIR /walletotpservice

# Copy the built jar file into the container
COPY target/walletotpservice.jar walletotpservice.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "walletotpservice.jar"]
