package com.moim.Triplanner.Member.Service;

import com.moim.Triplanner.Member.VO.User;

public interface MemberService {
    User register(User user);
    User findByEmail(String email);
}
