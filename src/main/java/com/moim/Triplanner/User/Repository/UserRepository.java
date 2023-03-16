package com.moim.Triplanner.User.repository;

import com.moim.Triplanner.User.VO.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO, Long> {
    UserVO findByEmail(String email);
}
