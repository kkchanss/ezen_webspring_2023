package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@RestController
@RequestMapping("member")
public class MemberController {
    // Controller -> Service 요청
    // Controller <- Service 응답
    @Autowired
    private MemberService memberService;

    // 1. [C] 회원가입
    @PostMapping("/post")
    public boolean postMember(@RequestBody MemberDto memberDto) {
        return memberService.postMember(memberDto);
    }

    // 2. [R] 회원정보 호출
    @GetMapping("/get")
    public MemberDto getMember(@RequestParam int mno) {
        return memberService.getMember(mno);
    }

    // 3. [U] 회원정보 수정
    @PutMapping("/put")
    public boolean updateMember(@RequestBody MemberDto memberDto) {
        return memberService.updateMember(memberDto);
    }
    // 4. [D] 회원탈퇴
    @DeleteMapping("/delete")
    public boolean deleteMember(@RequestParam int mno) {
        return memberService.deleteMember(mno);
    }

    // 5. 아이디 찾기
    @PostMapping("/findid")
    public String findId(@RequestBody MemberDto memberDto) {
        return memberService.findId(memberDto);
    }

    // 6. 비밀번호 찾기
    @PostMapping("/findpw")
    public String findPw(@RequestBody MemberDto memberDto) {
        return memberService.findPw(memberDto);
    }

    // 7. 로그인
    @PostMapping("/login")
    public boolean login(HttpSession session, @RequestBody MemberDto memberDto) {
        if(memberService.login(memberDto) == null) return false;
        session.setAttribute("memberSession", memberService.login(memberDto));
        return true;
    }

    // 8. 로그아웃
    @GetMapping("/logout")
    public boolean logout(HttpSession session) {

        MemberDto dto = (MemberDto) session.getAttribute("memberSession");
        if(dto != null) {
            session.removeAttribute("memberSession");
            return true;
        }
        return false;
    }
}
