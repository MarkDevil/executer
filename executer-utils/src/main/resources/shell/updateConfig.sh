#!/bin/sh

workspace=$1

approval_service=$workspace/test
approval_web=$workspace/test

find . -type f -name 'dubbo.properties' | xargs sed -i '1,$ s/127.0.0.1/test/g'