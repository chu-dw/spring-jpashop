package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext   //스프링 부트 엔티티 메니저 설정 자동등록
    private EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();  //커맨드와 커리 분리?
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
