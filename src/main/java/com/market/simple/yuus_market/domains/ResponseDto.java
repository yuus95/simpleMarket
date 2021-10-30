package com.market.simple.yuus_market.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    private int code;
    private String message;
    private T data;

}
