#!/usr/bin/env bash

# bash 스크립트는 값을 반환하는 기능이 없다. echo로 결과를 출력 후 클라이언트에서 그 값을 잡아서 사용한다. 중간에 echo를 사용하면 안된다.

# 쉬고 있는 profile 찾기: real1이 사용 중이면 real2가 쉬고 있고, 반대면 real1이 쉬고 있음

function find_idle_profile()
{
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

  if [ ${RESPONSE_CODE} -ge 400 ] # 400 보다 크면

  then
    CURRENT_PROFILE=real2
  else
    CURRENT_PROFILE= $(curl -s http://localhost/profile)
  fi

  if [ ${CURRENT_PROFIE} == real1 ]

  then
    IDLE_PROFILE=real2
  else
    IDLE_PROFILE=real1
  fi

  echo "${IDLE_PROFILE}"
}

# 쉬고 있는 profile prot 찾기

function find_idle_prot()
{
  IDLE_PROFILE=$(find_idle_profile)

  if [ ${IDLE_PROFILE} == real1 ]
  then
    echo "8081"
  else
    echo "8082"
  fi

}