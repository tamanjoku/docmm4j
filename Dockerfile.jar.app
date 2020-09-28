FROM openjdk:13-jdk-alpine
MAINTAINER Torti Ama-Njoku <torti.ama-njoku@tocchae.com>

ARG appJarFilename

RUN apk add tzdata fontconfig ttf-dejavu tomcat-native

COPY ./target/${appJarFilename}.jar app.jar

CMD ["java", "-jar", "app.jar"]
