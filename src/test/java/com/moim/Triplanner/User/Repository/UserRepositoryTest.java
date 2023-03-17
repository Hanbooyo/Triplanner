package com.moim.Triplanner.User.Repository;

import com.moim.Triplanner.User.VO.UserVO;
import com.moim.Triplanner.User.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateAndFindUser() {
        // given
        UserVO user = new UserVO();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setName("test");

        // when
        Optional<UserVO> foundUserOptional = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        UserVO foundUser = foundUserOptional.isPresent() ? foundUserOptional.get() : null;

        // then
        assertNotNull(foundUser);
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getPassword(), foundUser.getPassword());
        assertEquals(user.getName(), foundUser.getName());
    }
}
