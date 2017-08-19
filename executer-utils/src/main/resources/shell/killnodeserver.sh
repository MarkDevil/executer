#!/bin/sh
pid=`ps -ef | grep "node server" | grep -v grep | awk '{print $2}'`
echo $pid
if [ $pid != "" ]; then
    kill -9 $pid
fi

