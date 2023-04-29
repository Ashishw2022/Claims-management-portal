FROM openjdk:17
LABEL maintainer="irdaproject"
ADD target/irda-microservice-0.0.1-SNAPSHOT.jar springboot-docker-image.jar
ENTRYPOINT ["java", "-jar", "springboot-docker-image.jar"]