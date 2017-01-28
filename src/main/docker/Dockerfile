FROM openjdk:8-jdk 
VOLUME /tmp

ADD bot_demo-0.0.1-SNAPSHOT.jar app.jar
ADD startService.sh /startService.sh
RUN chmod 700 /startService.sh
ENTRYPOINT ./startService.sh 
