FROM openjdk:8-jdk-alpine

ADD https://github.com/alibaba/Sentinel/releases/download/1.6.0/sentinel-dashboard-1.6.0.jar /sentinel-dashboard-1.6.0.jar
ENTRYPOINT ["java", "-Dserver.port=8080", "-Dcsp.senti  nel.dashboard.server=localhost:8080", "-Dproject.name=sentinel-dashboard", "-jar", "/sentinel-dashboard-1.6.0.jar", "-Dfile.encoding=utf-8"]