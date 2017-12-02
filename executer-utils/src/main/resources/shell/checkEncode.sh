#!/usr/bin/env bash

SRC=/Users/mark/ziroom/ZiRoomAutomationApi

for file in $(find $SRC -name "*.java")
do
	if test -f $file
	then
    	file1=enca ${file}
    	echo ${file1}
    	if [ ${file1}x = "Unrecognized encoding"x ]; then
    	    continue
    	fi
    	#enca -L zh -x GBK $file
	fi
done