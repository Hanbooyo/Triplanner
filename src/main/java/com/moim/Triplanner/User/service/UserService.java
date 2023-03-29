package com.moim.Triplanner.User.service;

import com.moim.Triplanner.User.domain.User;
import com.moim.Triplanner.User.dto.LoginRequestDto;
import com.moim.Triplanner.User.dto.UserRequestDto;
import com.moim.Triplanner.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signUp(UserRequestDto userRequestDto) {
        // UserRequestDto에서 User 엔티티로 변환
        User user = userRequestDto.toEntity();

        // 회원가입 시점의 시간 설정
        user.setCreatedDate(new Date(System.currentTimeMillis()));

        // UserRepository를 통해 회원가입 처리
        return userRepository.save(user);
    }

    public String login(LoginRequestDto loginRequestDto) {
        // 로그인 시도하는 User 정보 조회
        User user = userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        // 로그인 요청한 User의 비밀번호 검증
        if (!user.getPassword().equals(loginRequestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 로그인 성공 시 JWT Token 생성 후 리턴
        return "JWT Token";
    }
}
