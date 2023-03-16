package com.moim.Triplanner.User.VO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class UserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Long id;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Gender", nullable = false)
    private String gender;

    @Column(name = "Birthday", nullable = false)
    private Date birthday;

    // 생성자, Getter, Setter 생략
}
