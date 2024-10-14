package com.example.demo.mapper;

import com.example.demo.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // 모든 사용자 정보를 조회하는 메서드
    List<User> findAll();

    // 사용자 정보를 추가하는 메서드
    void insert(User user);

    // 사용자 정보를 수정하는 메서드
    void update(User user);

    // 사용자 정보를 삭제하는 메서드
    void delete(Long id);

    // 사용자 ID로 사용자 정보를 조회하는 메서드
    User findById(Long id);
}
