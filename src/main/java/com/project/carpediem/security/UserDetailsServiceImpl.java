package com.project.carpediem.security;

import com.project.carpediem.entity.Member;
import com.project.carpediem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String studentNum) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByStudentNum(studentNum).orElseThrow(
                () -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return new UserDetailsImpl(findMember, findMember.getStudentNum());
    }

}
