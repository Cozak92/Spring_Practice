package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemeberRepository;
import hello.hellospring.repository.memoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Service

//스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커
//밋한다. 만약 런타임 예외가 발생하면 롤백한다.
//JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.
@Transactional
public class MemberService {

    private final MemeberRepository memeberRepository;


    //DI 인젝션 [ 생성자 주입, 필드 주입(중간에 바꿔치기를 할 수 없다.), Setter 주입(public 하게 노출),
    //@Autowired
    public MemberService( MemeberRepository memeberRepository){

        this.memeberRepository = memeberRepository;
    }

    //회원가입dla
   public long join (Member member){

       ValidateDuplicateMember(member); //중복회원 검증
       memeberRepository.save(member);
       return member.getId();
   }

    private void ValidateDuplicateMember(Member member) {
        //중복회원은 가입 X
        //Optional으로 감싸고 있기때문에 ifPresent로 바로 사용가능
        memeberRepository.findByName(member.getName())
             .ifPresent(m -> {
                 throw new IllegalStateException("이미 존재하는 회원입니다.");
              });
    }

    public List<Member> findMembers(){
       //전체 회원조회
        return memeberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memeberRepository.findById(memberId);
   }
}
