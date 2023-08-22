FROM amazoncorretto:17
EXPOSE 3000 5432 80
VOLUME /tmp
COPY target/*.jar Mariosy-1.jar
ENTRYPOINT ["java","-jar","/Mariosy-1.jar"]