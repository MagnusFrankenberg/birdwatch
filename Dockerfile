#base image to use
FROM amazoncorretto:17-alpine-jdk

#Setting Working directory in the container for the project
WORKDIR /app

#save jar-file to a variable
ARG JAR_FILE=./build/libs/birdwatch-0.0.1-SNAPSHOT.jar

# Copy the jar file into our app
COPY ${JAR_FILE} /app/app.jar
#COPY ./build/libs/springQuizGradleDockerApp-0.0.1-SNAPSHOT.jar /app


#Exposing port 8080 (meaning the containers application listen on port 8080)
#note that this command does not make the app accessible from host or outside the container
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "app.jar"]