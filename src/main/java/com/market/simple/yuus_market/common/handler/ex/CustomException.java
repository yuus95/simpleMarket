package com.market.simple.yuus_market.common.handler.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends  RuntimeException{
    private final ErrorCode errorCode;
}
