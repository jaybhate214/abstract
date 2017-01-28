#!/bin/sh

nodeName=${KUBE_ID}_bot_demo_0.0.1-SNAPSHOT_$(cat /proc/self/cgroup | grep docker | sed s/\\//\\n/g | tail -1)

touch /app.jar

java -Djava.security.egd=file:/dev/./urandom -Xms1024m -Xmx1024m -jar /app.jar

