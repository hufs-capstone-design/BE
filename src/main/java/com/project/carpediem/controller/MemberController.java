package com.project.carpediem.controller;


import com.project.carpediem.dto.request.LoginRequestDto;
import com.project.carpediem.dto.request.SignupRequestDto;
import com.project.carpediem.dto.response.MessageResponseDto;
import com.project.carpediem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@Tag(name = "MEMBER", description = "회원 관련 API Document")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회원가입하는 기능입니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/signup")
    public ResponseEntity<MessageResponseDto> signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        return memberService.signup(signupRequestDto);
    }

    @Operation(summary = "로그인 API", description = "로그인하는 기능입니다.")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/login")
    public ResponseEntity<MessageResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return memberService.login(loginRequestDto,response);
    }

}
