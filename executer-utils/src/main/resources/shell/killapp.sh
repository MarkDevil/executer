#!/bin/sh
APP_NAME=tomcat-executer
app=$1
#pid=$(`ps -ef | grep ${APP_NAME} | grep -v grep | awk '{print $2}'`)
#echo $pid
#if [ $pid ]; then
#    kill -9 $pid
#fi
function checkService(){
    echo input error msg ${app}
    container=$(`sudo docker ps | grep ${app}`)
    echo $container
    if [ $container ]; then
        sudo docker ps
    fi
    sudo docker images
}

checkService
