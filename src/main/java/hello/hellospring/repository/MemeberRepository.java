package hello.hellospring.repository;
import hello.hellospring.domain.Member;


import java.util.Optional;
import java.util.List;


public interface MemeberRepository {
    Member save(Member member);
    // Optional id나 name 이 null 일 수 있다
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
