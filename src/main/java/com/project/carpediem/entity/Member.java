package com.project.carpediem.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Optional;



@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentNum;

//    @Column(nullable = false, length = 20)
//    private String memberName;
//
//    @Column(nullable = false, length = 50)
//    private String email;

    @Column(nullable = false)
    private String password;


    @Builder
    private Member(String studentNum, String password) {
        this.studentNum = studentNum;
//        this.memberName = memberName;
//        this.email = email;
        this.password = password;

    }

    public static Member of(String studentNum, String password) {
        return Member.builder()
                .studentNum(studentNum)
//                .memberName(memberName)
//                .email(email)
                .password(password)
                .build();
    }

}
