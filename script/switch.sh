#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy(){
  IDLE_PORT=$(find_idle_ort)

  echo "> 전환할 Port $IDLE_PORT"
  echo "> Port 전환"
  echo "> set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc
  # 하나의 문장을 만들어 파이프라인으로 넘겨주기 위해 echo를 사용한다.

  echo "> nginx reload"
  sudo service nginx reload
}