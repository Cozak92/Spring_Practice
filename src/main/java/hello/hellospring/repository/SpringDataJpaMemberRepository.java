package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//jpaRepository를 받고있으면 스프링이 자동으로 구현체를 만들어서 Bean에 등록해준다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemeberRepository {


    @Override
    Optional<Member> findByName(String name);
}
