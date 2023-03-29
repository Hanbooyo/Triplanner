package com.moim.Triplanner.User.dto;

import com.moim.Triplanner.User.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private Long id;
    private String username;
    private String nickname;
    private String email;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
    }
}
