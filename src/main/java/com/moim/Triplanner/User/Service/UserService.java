package com.moim.Triplanner.User.Service;

import com.moim.Triplanner.User.VO.UserVO;

public interface UserService {
    UserVO getUser(Long userId);

    Long createUser(UserVO user);

    void updateUser(UserVO user);

    void deleteUser(Long userId);

    void signUp(UserVO user);
}
