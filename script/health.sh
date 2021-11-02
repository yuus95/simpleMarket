#!/usr/bin/env bash

ABSPATH=$(readlink -f $0) # 현재파일의 위치 찾기
ABSDIR=$(dirname $ABSPATH) #현재파일의 위치로 현대디렉토리명 가져오기
source ${ABSDIR}/profile.sh # profile.sh의 명령어를 읽어서 실행시킨다.
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port) #profile.sh 의 find_idle_port 함수 실행


# curl은 사용자 상호 작용 없이 작동하도록 설계된 서버에서 또는 서버로 데이터를 전송하기 위한 명령줄 유틸리티입니다.
echo "> Health Check Start!"
echo "> IDLE_PORT: $IDLE_PORT"
echo "> curl -s http://localhost:$IDLE_PORT/profile "
sleep 10

for RETRY_COUNT in {1..10}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l)

  if [ ${UP_COUNT} -ge 1 ]
  then # $up_count >= 1 ("real" 문자열이 있는지 검증)
      echo "> Health check 성공"
      switch_proxy
      break
  else
      echo "> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다."
      echo "> Health check: ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 10 ]
  then
    echo "> Health check 실패. "
    echo "> 엔진엑스에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done