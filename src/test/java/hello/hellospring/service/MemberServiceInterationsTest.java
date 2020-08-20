package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemeberRepository;
import hello.hellospring.repository.memoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
//데이터베이스에는 다들 트랜잭션 기능이있다. 테스트가 끝나면 롤백해준다.
//각각의 테스트 메소드 마다 트랜잭션이 기능
//@Commit을 붙이면 자동으로 commit한다.
@Transactional
class MemberServiceInterationsTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemeberRepository repository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("Shin");

        //when

        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    void DupJoin(){
        //given

        Member member1 = new Member();
        member1.setName("Shin");

        Member member2 = new Member();
        member2.setName("Shin");

        //when

        memberService.join(member1);

        //뒤에 메소드를 실행했을때 앞에있는것이 실행 되었는지 확인하는 것

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join((member2)));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e){
            System.out.println(e);
        }*/




        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}