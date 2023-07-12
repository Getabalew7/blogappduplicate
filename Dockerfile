FROM openjdk:17-jdk-alpine

# Set the environment variables for the PostgreSQL database
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD secret
ENV POSTGRES_DB mydb
# Install Maven
RUN apk add --no-cache maven
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

EXPOSE 8855

CMD ["java", "-jar", "target/blogapp-0.0.1-SNAPSHOT.jar"]
