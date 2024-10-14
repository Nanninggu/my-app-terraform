package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {

    // 예외 메시지를 받아서 RuntimeException 생성자에 전달하는 생성자
    public UserNotFoundException(String message) {
        super(message);
    }
}
