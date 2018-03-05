#!/bin/sh

workspace=/houbank/server/workspace/bjtest-45-hb_approval_service

approval_service=$workspace/hb_approval_service
approval_web=$workspace/hb_approval_web

find . -type f -name 'dubbo.properties' | xargs sed -i '1,$ s/127.0.0.1/192.168.18.45/g'
find . -type f -name 'mysql.properties' | xargs sed -i '1,$ s/116.236.184.237:8003/192.168.18.45:3307/g'
find . -type f -name 'mysql.properties' | xargs sed -i '1,$ s/192.168.13.18:3306/192.168.18.45:3307/g'
find . -type f -name 'redis.properties' | xargs sed -i '1,$ s/192.168.13.64/192.168.18.45/g'