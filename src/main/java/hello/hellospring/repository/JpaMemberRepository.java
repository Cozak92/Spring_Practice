package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemeberRepository {

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    //jpa는 EntityManager로 모든것이 동작함
    //스프링부트가 자동으로 Entity 매니저를 만들어줌
    private final EntityManager em;


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {

        List<Member> resultList = em.createQuery("select m from Member m where m.name = :name ", Member.class)
                .setParameter("name", name)
                .getResultList();

        return resultList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //JPQL 객체지향 쿼리를 써야함
        //엔티티를 대상으로 쿼리를 날림
        //Select m을 통해 객체 자체를 셀렉트함

        List<Member> resultList = em.createQuery("select m from Member m", Member.class).getResultList();
        return resultList;
    }
}
