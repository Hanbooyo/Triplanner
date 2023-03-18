package com.moim.Triplanner.User.Repository;

import com.moim.Triplanner.User.VO.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    /* OAuth */
    Optional<User> findByEmail(String email);
}