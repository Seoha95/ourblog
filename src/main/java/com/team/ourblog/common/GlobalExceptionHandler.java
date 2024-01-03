package com.team.ourblog.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //특정 에러 발생 시 controller에 발생하였을 경우 해당 에러를 캐치하여 클라이언트로 오류를 반환 하도록 처리하는 기능
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<String> handleMemberException(MemberException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatus());
    }
}
