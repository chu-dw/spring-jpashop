package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor -> 생성자 자동 생성, 레파지코리도 간략화 가능
public class MemberService {


    private final MemberRepository memberRepository;

    @Autowired  //생성자 하나면 생략 가능
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    //회원 가입
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();  //영속성 항상 값 있음 보장
    }

    private void validateDuplicateMember(Member member){
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    @Transactional(readOnly = true) //조회 최적화
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 단건 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
