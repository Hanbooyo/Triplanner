package com.moim.Triplanner.Member.Repository;

import com.moim.Triplanner.Member.VO.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
