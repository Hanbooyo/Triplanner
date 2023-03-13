package com.moim.Triplanner.Member.Service;

import com.moim.Triplanner.Member.VO.User;

public interface UserService {
    User getUserByEmail(String email);
    void saveUser(User user);
}
