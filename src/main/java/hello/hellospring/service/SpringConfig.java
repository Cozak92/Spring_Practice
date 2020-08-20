package hello.hellospring.service;

import hello.hellospring.AOP.TimeTraceAOP;
import hello.hellospring.repository.*;
import net.bytebuddy.asm.Advice;
import org.hibernate.persister.walking.spi.EntityIdentifierDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.beans.BeanProperty;

@Configuration
public class SpringConfig {

    //개방-폐쇄 원칙(OCP, Open-Closed Principle)
    //확장에는 열려있고, 수정, 변경에는 닫혀있다.
    //스프링의 DI (Dependencies Injection)을 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경할 수 있다.

    /**
     * @Autowired
     * public SpringConfig(DataSource dataSource) {
     * this.dataSource = dataSource;
     * }
     * private DataSource dataSource;
     * private EntityManager em;
     * @Autowired
     * public SpringConfig(EntityManager em) {
     * this.em = em;
     * }
     **/

    private final MemeberRepository memeberRepository;

    @Autowired
    public SpringConfig(MemeberRepository memeberRepository) {
        this.memeberRepository = memeberRepository;
    }

//    @Bean
//    public MemeberRepository memeberRepository(){
//
//
//        //return  new JpaMemberRepository(em);
//        //return new JdbcTemplatesMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//        //return new memoryMemberRepository();
//
//    }

    @Bean
    public MemberService memberService(){

        return new MemberService(memeberRepository);
    }


    //AOP 메소드의 공통 관심사항과 핵싱관심사항을 나눠 개발발


}
