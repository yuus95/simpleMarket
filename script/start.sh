#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

REPOSITORY=/home/git
PROJECT_NAME=simpleMarket2


echo "> Build 파일 복사"
echo  "> $REPOSITORY/$PROJECT_NAME/ $REPOSITORY/dep2"
cp $REPOSITORY/$PROJECT_NAME/*.jar $REPOSITORY/dep2

echo "> 새애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/dep2/*.jar | tail -n 1)

echo "> JAR_NAME : $JAR_NAME"


echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 를 profile=$IDLE_PROFILE 로 실행합니다."

IDLE_PROFILE=$(find_idle_profile)

nohup java -jar -Dspring.profiles.active=$IDLE_PROFILE $JAR_NAME >$REPOSITORY/dep2/nohup.out 2>&1 &
