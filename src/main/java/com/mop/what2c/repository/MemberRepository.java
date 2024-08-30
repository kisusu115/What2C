package com.mop.what2c.repository;

import com.mop.what2c.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String username);

    boolean existsByUsername(String username);

    // JPQL을 사용한 쿼리
    //@Query("SELECT m FROM Member m WHERE m.username = :username AND m.age > :age")
    //List<Member> findMembersByUsernameAndAge(@Param("username") String username, @Param("age") int age);

    // 네이티브 SQL 쿼리
    //@Query(value = "SELECT * FROM member WHERE email = :email", nativeQuery = true)
    //Optional<Member> findByEmailNative(@Param("email") String email);
}
