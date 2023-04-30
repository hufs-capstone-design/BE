package com.project.carpediem.service;

import com.project.carpediem.dto.request.LoginRequestDto;
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

        String studentNum = signupRequestDto.getStudentNum();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));

        Optional<Member> findStudentNum = memberRepository.findByStudentNum(studentNum);
        if (findStudentNum.isPresent()) {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        }

        Member member = Member.of(studentNum, password);
        memberRepository.save(member);

        return ResponseEntity.ok()
                .body(MessageResponseDto.of(HttpStatus.OK.value(), "회원가입 성공"));
    }

    public ResponseEntity<MessageResponseDto> login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String studentNum = loginRequestDto.getStudentNum();
        String password = loginRequestDto.getPassword();

        Optional<Member> foundMember = memberRepository.findByStudentNum(studentNum);
        if(foundMember.isEmpty()){
            throw new IllegalArgumentException("존재하지 않는 사용자 입니다.");
        }

        if(!passwordEncoder.matches(password, foundMember.get().getPassword())){
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER,jwtUtil.createToken(foundMember.get().getStudentNum()));

        return ResponseEntity.ok()
                .body(MessageResponseDto.of(HttpStatus.OK.value(), "로그인 성공"));
    }


}
