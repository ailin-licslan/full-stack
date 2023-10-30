FROM openjdk:8-jdk-slim

MAINTAINER LICSLAN

COPY target/*.jar /boot.jar

ENTRYPOINT ["java","-jar","/boot.jar"]



# 构建镜像
# docker build -t boot .



# 启动容器
# docker run --name boot-9999 -e server.port=9999 -e JAVA_OPTS='-Xms128m -Xmx256m' -p 9999:9999 -d boot
# docker run --name boot-9998 -e server.port=9998 -e JAVA_OPTS='-Xms128m -Xmx256m' -p 9998:9998 -d boot
# docker run --name boot-9997 -e server.port=9997 -e JAVA_OPTS='-Xms128m -Xmx256m' -p 9997:9997 -d boot




# 允许远程登录
# use mysql;
# update user set host = ‘%’ where user = “root”;
# flush privileges;
# select host,user from user;


