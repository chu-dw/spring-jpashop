package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        //valid가 JAson 바꿔줌
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }//얘는 name 필수로 설정 안됨 -> member entity에 @notempry -> 너무 깊게 들어감
    //엔티티 컨트롤러에서 직접 갖다 쓰면 나중에 힘들어짐


    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){

        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }



    @Data
    static class CreateMemberRequest{
        @NotEmpty
        private String name;
    }


    @Data
    static class CreateMemberResponse{
        private Long id;
        public CreateMemberResponse(Long id) {
            this.id = id;
        }

    }
}
