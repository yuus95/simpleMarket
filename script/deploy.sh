#/bin/bash

REPOSITORY=/home/git
PROJECT_NAME=simpleMarket

cd $REPOSITORY/$PROJECT_NAME/

echo ">>Build 파일복사"
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/dep

echo "> 현재 구동중인 pid확인"
CURRNET_PID=${pgrep -f yuus_market.*.jar}

echo "현재 구동중인 애플리케이션 pid :$CURRENT_PID"

if [ -z "$CURRENT_PID" ]
then
	echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
	echo ">> kill -15 $CURRENT_PID"
	kill -15 $CURRENT_PID
	sleep 5
fi

# ls -t(최근 수정된파일)
# ls -r 알파벳 역순으로 표시
# dep 디렉토리에서 jar 목록을 검색후 시간1번째 꺼를 사용
echo ">새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/dep/ | grep jar | tail -n 1)


# 백그라운드 프로세스를 활용하여 실행
echo ">> JAR NAME : $JAR_NAME"
echo ">> Jar 권한 변경"
chmod +x $JAR_NAME

nohup java -jar $REPOSITORY/dep/$JAR_NAME >$REPOSITORY/dep/nohup.out 2>&1 &