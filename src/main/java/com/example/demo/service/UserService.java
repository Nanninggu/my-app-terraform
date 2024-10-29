package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    // UserMapper를 주입받아 사용
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 사용자 정보 조회 (호출한 곳으로 반환한다.)
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    // 사용자 생성
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userMapper.insert(user);
        return user;
    }

    // 사용자 정보 수정
    public User updateUser(Long id, String name, String email) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        userMapper.update(user);
        return user;
    }

    // 사용자 정보 삭제
    public void deleteUser(Long id) {
        userMapper.delete(id);
    }

    // 사용자 ID로 사용자 정보 조회
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }
}
