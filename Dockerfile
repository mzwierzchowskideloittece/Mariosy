FROM amazoncorretto:17
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar example-1.jar
ENTRYPOINT ["java","-jar","/example-1.jar"]