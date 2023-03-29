package com.moim.Triplanner.User.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    public void encryptPassword() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
    }
}

