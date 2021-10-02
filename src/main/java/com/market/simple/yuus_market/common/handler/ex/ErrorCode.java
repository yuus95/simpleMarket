package com.market.simple.yuus_market.common.handler.ex;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),
    ACCOUNT_OVER(BAD_REQUEST,"계좌는 최대 5개 등록할 수 있습니다."),
    VALID_ERROR(BAD_REQUEST,""),
    CANNOT_REDIRECT_URI(BAD_REQUEST,"URI를 찾을 수 없습니다."),
    CANNOT_PROVIDER(BAD_REQUEST,"요청을 승인할 수 없습니다."),



    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    NOT_TOKEN(UNAUTHORIZED,"JWT토큰이 없습니다."),
    INVALID_AUTH_TOKEN(UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    NOT_MATCHED_PASSWORD(UNAUTHORIZED,"비밀번호가 일치하지 않습니다."),


    // 403 Forbidden : 요청이 서버에 의해 거부되었음



    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    REFRESH_TOKEN_NOT_FOUND(NOT_FOUND, "로그아웃 된 사용자입니다"),
    NOT_FOLLOW(NOT_FOUND, "팔로우 중이지 않습니다"),
    NOT_MATCHED_EMAIL(NOT_FOUND,"이메일을 확인해주세요"),
    NOT_MATCHED_ID(NOT_FOUND,"아이디를 확인해주세요"),
    CANNOT_FIND_ACCOUNT(NOT_FOUND,"해당 계좌를 등록하지 않았습니다."),
    CANNOT_FIND_BOARD(NOT_FOUND,"해당 계좌를 등록하지 않았습니다."),




    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),
    DUPLICATE_MEMBER(CONFLICT,"이미 존재하는 멤버입니다"),
    DUPLICATE_ACCOUNT(CONFLICT,"등록된 계좌입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}