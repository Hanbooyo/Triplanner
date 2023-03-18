package com.moim.Triplanner.User.VO;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

    private Long id;
    private String email;
    private String name;

    public UserSessionDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getNickname();
    }
}
