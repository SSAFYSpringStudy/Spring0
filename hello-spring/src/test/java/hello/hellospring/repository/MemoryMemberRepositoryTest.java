package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Repository
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //method 실행이 끝날 때마다 어떤 동작을 한다. -> 콜백 메소드
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member =new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

         Member result = repository.findByName("spring1").get();
//        Member result = repository.findByname("spring2").get(); 라고 하면
//        result는 member2이기 때문에 밑줄이 정상 동작 되지 않는다. (다른 객체다.)
         assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
