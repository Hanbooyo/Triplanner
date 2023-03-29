package com.moim.Triplanner.User.controller;

import com.moim.Triplanner.User.domain.User;
import com.moim.Triplanner.User.dto.LoginRequestDto;
import com.moim.Triplanner.User.dto.UserRequestDto;
import com.moim.Triplanner.User.dto.UserResponseDto;
import com.moim.Triplanner.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto userRequestDto) {
        User user = userService.signUp(userRequestDto);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = userService.login(loginRequestDto);
        return ResponseEntity.ok(token);
    }
/*
    @RestController : 해당 클래스가 REST API 요청을 처리하는 컨트롤러임을 명시합니다.

    @RequestMapping : 컨트롤러에 대한 RequestMapping을 지정합니다.

    @RequiredArgsConstructor : 생성자를 자동으로 만들어줍니다.

    @PostMapping : POST 요청을 처리합니다.

    @RequestBody : Request Body를 읽어서 해당 객체에 매핑합니다.

    @PathVariable : URL의 일부를 파라미터로 사용합니다.

    ResponseEntity : 응답에 대한 전체적인 정보를 담는 클래스입니다. status code, headers, body 등을 포함합니다. 반환할 데이터가 있는 경우에는 body에 데이터를 넣어서 반환합니다.

 */

}
