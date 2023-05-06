# Setting JDK 11: The Default of this project
FROM openjdk:11-jre-slim

# Running the Jar File of this project
WORKDIR /app
COPY build/libs/MaternityCalculationsAPI-0.0.1-SNAPSHOT*.jar /app/MaternityCalculationsAPI-0.0.1-SNAPSHOT.jar.jar

# Running the JAR File
EXPOSE 8080
CMD ["java", "-jar", "MaternityCalculationsAPI-0.0.1-SNAPSHOT.jar"]
