# Build application
FROM adoptopenjdk/maven-openjdk12 AS build
COPY src /cstv/src
COPY pom.xml /cstv
RUN mvn -f /cstv/pom.xml clean package spring-boot:repackage

# Run application
FROM openjdk:12
COPY --from=build /cstv/target/cstv.jar /usr/local/lib/cstv.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/cstv.jar"]