package com.moim.Triplanner.User.Service;

import com.moim.Triplanner.User.VO.UserVO;
import com.moim.Triplanner.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    @Override
    public UserVO getUser(Long userId) {
        Optional<UserVO> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            String message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
            throw new IllegalArgumentException(message);
        }
        return userOptional.get();
    }

    @Override
    public Long createUser(UserVO user) {
        return userRepository.save(user).getId();
    }

    @Override
    public void updateUser(UserVO user) {
        Optional<UserVO> userOptional = userRepository.findById(user.getId());
        if (userOptional.isEmpty()) {
            String message = messageSource.getMessage("user.not.found", null, LocaleContextHolder.getLocale());
            throw new IllegalArgumentException(message);
        }
        UserVO existingUser = userOptional.get();
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setName(user.getName());
        existingUser.setGender(user.getGender());
        existingUser.setBirthday(user.getBirthday());
        userRepository.save(existingUser);
    }


    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void signUp(UserVO user) {
        userRepository.save(user);
    }
}
