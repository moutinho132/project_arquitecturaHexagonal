FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
# Agrega estas líneas para descargar e incluir el driver MySQL
RUN mkdir -p /opt/lib
ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar /opt/lib/mysql-connector-java.jar

ADD ./target/test-0.0.1-SNAPSHOT.jar microservicio.jar
WORKDIR /opt/app
ADD ./target/test-0.0.1-SNAPSHOT.jar /opt/app/microservicio.jar
WORKDIR /opt/app
ENTRYPOINT ["java", "-Dspring.config=.", "-Dspring.profiles.active=local", "-Dkeystore.file=mykey.p12", "-jar", "microservicio.jar"]
