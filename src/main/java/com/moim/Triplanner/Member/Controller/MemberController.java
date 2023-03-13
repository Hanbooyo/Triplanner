package com.moim.Triplanner.Member.Controller;

import com.moim.Triplanner.Member.Service.MemberService;
import com.moim.Triplanner.Member.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        // password 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        boolean isCreated = memberService.createUser(user);
        if (isCreated) {
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn() {
        return new ResponseEntity<>("User logged in successfully", HttpStatus.OK);
    }
}
