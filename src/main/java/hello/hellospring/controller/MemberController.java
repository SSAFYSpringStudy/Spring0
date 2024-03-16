package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //이 어노테이션을 보고 스프링이 controller를 객체로 생성해서 스프링 컨테이너에 들고 있는다.
public class MemberController {
//    private final MemberService memberService = new MemberService();
    //위와 같이 new로 선언하면 여러 용도로 쓰일때마다 인스턴스가 생성되므로 별로
    // 공용으로 하나만 만들고 쓰면 된다.
    private final MemberService memberService;
    @Autowired //스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결시킨다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members =memberService.findMembers();
        model.addAttribute("members",members);
        return"members/memberList";
    }
}
