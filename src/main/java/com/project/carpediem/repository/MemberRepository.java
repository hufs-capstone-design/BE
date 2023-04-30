package com.project.carpediem.repository;

import com.project.carpediem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
//    Optional<Member> findByEmail(String email);

    Optional<Member> findByStudentNum(String studentNum);
}
