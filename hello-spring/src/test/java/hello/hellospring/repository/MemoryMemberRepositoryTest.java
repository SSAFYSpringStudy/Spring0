package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 테스트하면서 저장되는 값을 정리해야 한다 -> clear
    // 테스트가 끝날 때마다 동작하는 메서드
    @AfterEach
    public void afterEach() {
        // store 비우기
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 1. 매번 글자로 확인해야 함
        // System.out.println("result = " + (result == member));

        // 2. 녹색 혹은 빨간색 오류 로그가 나옴
        // result or null
        // Assertions.assertEquals(member, null);

        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {

        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // spring2를 넣으면 빨간 오류 로그 발생
        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
