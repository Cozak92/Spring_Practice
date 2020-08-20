package hello.hellospring.domain;
//멤버 구현을 위한 클래스
//JPA를 위해선 엔티티로 맵핑을 해야한다.
//jpa는 인터페이스만 제공하고 hybernate등으로 구현체등 있다.

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    //db가 알아서 생성해주는 것 IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //만약 DB내의 컬럼명이 username일 경우;
    //@Column(name = "username")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

