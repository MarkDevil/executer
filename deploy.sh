#!/usr/bin/env bash
#set project path
workpath=/Users/Shared/gitWorkspace/executer
warpath=/Users/Shared/gitWorkspace/executer/executer-web/target
projectname=executer-web
warfile=${warpath}/${projectname}.war

cd ${workpath}
rm -rf ${warfile}
mvn clean package -DtestsSkip=true
if [ -f ${warfile} ]; then
    sudo docker build -t executer:lastest . || exit
    sudo docker-compose up -d
    sudo docker rmi $(sudo docker images -f "dangling=true" -q) || continue
fi

