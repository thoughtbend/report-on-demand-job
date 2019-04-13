FROM openjdk:8-jdk-alpine

# VOLUME /tmp

# ARG JAR_FILE

WORKDIR /app

LABEL application="report-on-demand-job" api-layer="batch"

COPY target/report-on-demand-job-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]