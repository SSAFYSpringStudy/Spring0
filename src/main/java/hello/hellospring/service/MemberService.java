package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { //외부에서 넣어주도록 변환
        this.memberRepository = memberRepository;
    }

    /**
     *회원 가입
     */
    public Long join(Member member){
        //같은 이름의 중복 회원은 안된다.
        validateDuplicateMember(member);
        memberRepository.save(member);
     return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{  //Optional덕분에 사용가능한 기능
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //서비스는 비즈니스에 의존적으로 보통 설계를 하고
    //리포지토리는 약간 더 기계적 개발스러운 용어를 사용한다.
    //롤에 맞는 네이밍하기
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
