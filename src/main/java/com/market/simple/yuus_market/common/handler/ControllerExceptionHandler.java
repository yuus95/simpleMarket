package com.market.simple.yuus_market.common.handler;



import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.handler.ex.CustomValidationException;
import com.market.simple.yuus_market.common.handler.ex.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 컨트롤에 익셉션을 다 낚아챈다.
 * 나중에사용하기
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    
    

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<ErrorResponse> CustomValidationException(CustomValidationException e){
        log.error("handleCustomValidationException throw CustomException: {}", e.getMessage());

        return ErrorResponse.toValidationResponseEntity(e.getMessage());
    }
    

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error("handleCustomException throw CustomException: {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
