#!/usr/bin/env bash
changeList=/Users/mark/hbFinancial/hbadmin-change-list
cd $changeList
if echo $PWD -eq $changeList;then
    echo "开始同步hbadmin list"
    git pull
else
    echo "切换目录不正确请检查!"
fi



