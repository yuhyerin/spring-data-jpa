package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember(){
        Member member = new Member("hyerin");
        // 인터페이스 밖에 없는데, 기능이 동작한다! -> 어떻게 된 거지???
        // Spring Data Jpa가 구현체를 알아서 만들어서 주입해준다.
        Member savedMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 같은 트랜잭션 내에서는 영속성 컨텍스트의 동일성을 보장한다. (1차캐시 기능)
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}
