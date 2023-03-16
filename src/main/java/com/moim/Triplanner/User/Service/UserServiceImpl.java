package com.moim.Triplanner.User.Service;

import com.moim.Triplanner.User.VO.UserVO;
import com.moim.Triplanner.User.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserVO getUser(Long userId) {
        Optional<UserVO> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID: " + userId);
        }
        return userOptional.get();
    }

    @Override
    @Transactional
    public Long createUser(UserVO user) {
        return userRepository.save(user).getUserId();
    }

    @Override
    @Transactional
    public void updateUser(UserVO user) {
        Optional<UserVO> userOptional = userRepository.findById(user.getUserId());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid user ID: " + user.getUserId());
        }
        UserVO existingUser = userOptional.get();
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public void signUp(UserVO user) {
        userRepository.save(user);
    }
}
