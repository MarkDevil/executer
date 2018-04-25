#!/bin/bash
### crontab -e
### /root/tool/ssServer.sh
###
pid=$(ps -ef | grep /usr/local/bin/ssserver | grep -v grep | awk '{print $2}')
echo "SS pid:" ${pid}
if [ "$pid" = "" ]; then
    WARN_MSG="SS server is gone, will restart it."
    echo ${WARN_MSG}
    /usr/bin/python /usr/local/bin/ssserver -c /etc/shadowsocks-python/config.json -d start
else
    kill -9 ${pid}
    /usr/bin/python /usr/local/bin/ssserver -c /etc/shadowsocks-python/config.json -d start
    echo "SS server is running"
fi
