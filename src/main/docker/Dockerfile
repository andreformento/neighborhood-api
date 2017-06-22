FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Andre Formento <andreformento.sc@gmail.com>

VOLUME /tmp
ADD neighborhood-api*.jar app.jar
# ADD properties.json properties.json
# ADD provinces.json provinces.json
# RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
