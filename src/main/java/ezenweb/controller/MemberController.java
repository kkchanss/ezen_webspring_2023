package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// IOC : 제어역전 ( 객체 관리를 스프링에게 위임 = 왜? 개발자가 편하려고/협업하려고(객체 공유해서 쓰려고))
// DI(Dependency injection) : 의존성 주입 [ 스프링이 객체를 관리하리까.. 스프링에게 객체를 의존(부탁)해서 주입(가져오기)]
@RestController // 컨트롤러(@Component 포함 = 스프링컨테이너(스프링 관리하는 메모리 공간) 빈(객체) 등록) + ResponseBody
@RequestMapping("/member") // 클래스 매핑
//@CrossOrigin("http://192.168.17.123:3000") // HTTP 헤더[Access-Control-Allow-Origin]에 교차 리소스 공유
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

 /*   // 2. [R] 회원정보 호출
    @GetMapping("/get")
    public MemberDto getMember(@RequestParam int mno) {
        return memberService.getMember(mno);
    }
*/
     // 세션 구현 한[로그인된 회원정보] 정보 호출
     // 회원 정보 호출
     @GetMapping("/get")
     public MemberDto doGet(){
         MemberDto result = memberService.getMember();
         return result;
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

 /*
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
*/
    // 7. 로그인
    @PostMapping("/login")
    public boolean login(@RequestBody MemberDto memberDto) {
        return memberService.login(memberDto);
    }

    // 8. 로그아웃
    @GetMapping("/logout")
    public boolean logout() {

        //return memberService.logout();
        return false;
    }

    // 9. [R] [이메일 중복검사]
    @GetMapping("/findMemail")
    public boolean getFindMemail(@RequestParam String memail)
    {
        System.out.println("memail = " + memail);
        //return memberService.findByMemail(memail);
        return memberService.getFindMemail(memail);
    }


}
