package com.example.hellospring.repository;

import com.example.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL: select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
