# 启动脚本
# start 启动参数，启动指定工程项目
# clean 删除指定目录的日志文件
#!/bin/sh
cmds=$1
echo $cmds
projectArray=("test")
workpath=/Users

cd $workpath || exit
if  [ "$cmds" = "start" ]; then
    for p in "${projectArray[@]}";do
        echo "${p}"/bin
        PID=`ps -ef | grep "${p}" |grep classpath |grep -v grep |awk '{print $2}'`
        if [ "${PID}" != "" ];then
            echo "Kill ${p} "
            kill -9 "${PID}"
        fi
        # sh $p/bin/startup.sh
        cd ../..
    done
fi

if [ "${cmds}" = "clean" ]; then
    for p in "${projectArray[@]}";do
        echo "${p}"/logs
        cd "${p}"/logs
        :> catalina.out || exit
        echo "clean up finished"
        cd ../..
    done
fi


