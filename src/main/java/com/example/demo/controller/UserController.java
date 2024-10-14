package com.example.demo.controller;

import com.example.demo.dto.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;
import com.example.demo.util.DateUtil;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // UserService를 주입받아 사용
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 모든 사용자 정보 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 사용자 생성
    @PostMapping("/create")
    public User createUser(@RequestParam String name,
                           @RequestParam String email) {
        return userService.createUser(name, email);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String email) {
        return userService.updateUser(id, name, email);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // 강제로 예외를 발생시키는 메서드
    @GetMapping("/forceException")
    public String forceException() {
        // 강제로 UserNotFoundException 예외를 발생시킵니다.
        throw new UserNotFoundException("This is a forced exception for testing purposes.");
    }

    // 강제로 전역 예외를 발생시키는 메서드
    @GetMapping("/forceGlobalException")
    public String forceGlobalException() {
        // 강제로 RuntimeException 예외를 발생시킵니다.
        throw new RuntimeException("테스트를 위한 강제 글로벌 예외입니다.");
    }

    // 현재 날짜를 반환하는 API
    @GetMapping("/currentDate")
    public LocalDate getCurrentDate() {
        return DateUtil.getCurrentDate();
    }
}
