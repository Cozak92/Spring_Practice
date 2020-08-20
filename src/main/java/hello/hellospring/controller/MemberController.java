package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


//스프링이 시작할때 스프링 컨테이너에 컨트롤러를 만들어서 넣어두고 관리함
@Controller
public class MemberController {




    //new로 만들게 되면 여러 컨트롤러들이 멤버컨트롤러를 가져다 쓸수있음
    // 별 기능이 없기때문에 여러 인스턴스를 만들 필요가 없음 , 공용으로 쓰면됨
    private final MemberService memberService;

    // 그러므로 스프링 컨테이너에 등록해두면 하나만 쓸수있기 때문에 등록해두자
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    //post 시 밑에 메소드가 호출되고 MemberForm에서 스프링이 자동으로 MemberForm.setname을 해준다.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join((member));

        return "redirect:/";
    }

    @GetMapping("/members")
    public String List(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
