package com.moim.Triplanner.User.Controller;

import com.moim.Triplanner.User.VO.UserVO;
import com.moim.Triplanner.User.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserVO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping("")
    public ResponseEntity<Void> signUp(@RequestBody UserVO userVO) {
        userService.createUser(userVO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable Long userId, @RequestBody UserVO userVO) {
        userVO.setId(userId);
        userService.updateUser(userVO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}