FROM ascdc/jdk8:latest

MAINTAINER chchmlml <<A href="mailto:chchmlml@163.com@163.com">chchmlml@163.com>

RUN mkdir data
WORKDIR /data

ENV JAVA_HOME=/usr/java/jdk
ENV JAVA_BIN=/usr/java/jdk/bin
ENV PATH=$PATH:$JAVA_HOME/bin
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

ADD target/bin/app.jar app.jar
EXPOSE 12001
ENTRYPOINT ["java","-jar","/data/app.jar"]




