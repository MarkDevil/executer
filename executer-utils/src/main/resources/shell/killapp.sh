#!/bin/sh
APP_NAME=tomcat-executer
pid=$(`ps -ef | grep ${APP_NAME} | grep -v grep | awk '{print $2}'`)
echo $pid
if [ $pid ]; then
    kill -9 $pid
fi

