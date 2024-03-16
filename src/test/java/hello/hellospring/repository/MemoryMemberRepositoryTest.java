package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
     repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);
        Member result =repository.findById(member.getId()).get(); //Optional이 붙어있으므로 get으로 꺼낸다. 좋은 방식은 아니지만 테스트 코드이므로 대충
        //검증
//        System.out.println("result = "+(result==member));
        //위와 같이 글자로 직접 볼수있지만 assert를 사용하자

        //junit assertions
//        Assertions.assertEquals(member,result); //녹색 불이 떳다면 통과, 따로 무언가 나오진 않음
        //assertj assertions
        assertThat(member).isEqualTo(result);

        //위 둘중 하나 아무거나 사용가능 assertj가 더 편리 - > 인자 순서가 상관없다.
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
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

        //실행시 member1과 member2가 함수 두개에서 모두 선언하므로 에러가 남
        //순서가 중요
        //-> 테스트를 끝내면 데이터를 클리어해줘야 함
        //AfterEach 만들기, 본문 리포지토리 가서 지우는 함수 만들기
        //테스트는 서로 의존관계가 없이 설계가 되어야 한다.
    }
}
