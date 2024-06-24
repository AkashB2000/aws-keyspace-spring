# Use OpenJDK 11 as base image
FROM openjdk:17.0-jdk-oraclelinux8

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container
COPY target/keyspace-0.0.1-SNAPSHOT.jar /app/app.jar

#COPY src/main/resources/application.conf /app/config/application.conf
#COPY cassandra_truststore.jks /app/config/cassandra_truststore.jks

#ENV CONTACT_POINTS=cassandra.ap-south-1.amazonaws.com:9142
#ENV LOCAL_DATACENTER=ap-south-1
#ENV AWS_REGION=ap-south-1
#ENV AWS_ROLE_ARN=arn:aws:iam::759464777243:role/keyspaceAccessRole


# Expose the port that the Spring Boot application uses (default is 8080)
EXPOSE 8080

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]