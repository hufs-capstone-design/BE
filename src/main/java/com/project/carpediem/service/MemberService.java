package com.project.carpediem.service;

import com.project.carpediem.dto.request.SignupRequestDto;
import com.project.carpediem.dto.response.MessageResponseDto;
import com.project.carpediem.entity.Member;
import com.project.carpediem.jwt.JwtUtil;
import com.project.carpediem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<MessageResponseDto> signup(SignupRequestDto signupRequestDto) {

        String memberName = signupRequestDto.getMemberName();
        Long studentNum = signupRequestDto.getStudentNum();
        String email = signupRequestDto.getEmail();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        Optional<Member> found = memberRepository.findByEmail(email);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }

        Member member = Member.of(studentNum, memberName, email, password);
        memberRepository.save(member);

        return ResponseEntity.ok()
                .body(MessageResponseDto.of(HttpStatus.OK.value(), "회원가입 성공"));
    }

}
