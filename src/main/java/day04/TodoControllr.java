package day04;

// 스프링 빈 : 스프링 컨테이너(저장소)에 저장된 객체 [ 이유 : 스프링이 대신 객체 관리 => 여러 개발자들이 작업했을때 기준 ]

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller // Spring MVC 중 해당 클래스를 Controller로 사용 / 스프링 컨테이너 빈 등록
@RestController // Controller + ResponseBody
@RequestMapping("/todo") // HTTP로부터의 해당 클래스의 매핑 주소 만들기 // 공통 URL
public class TodoControllr {
    // REST : HTTP기반으로 GET, POST, PUT, DELETE 메소드 이용한 웹 서비스
    // 1. [C]
    @PostMapping("") // HTTP 요청중 POST 메소드 요청할때
    //@ResponseBody   // 응답객체 자동 지원
    public boolean doPost(@RequestBody TodoDto todoDto) { // 요청 매개변수 : 입력받은 정보들 [ Dto }
        System.out.println("TodoControllr.doPost");
        System.out.println("todoDto = " + todoDto);
        return false;
    }

    // 2. [R]
    @GetMapping("") //@ResponseBody
    public List<TodoDto> doGet() {
        System.out.println("TodoControllr.doGet");
        return false;
    }

    // 3. [U]
    @PutMapping("") //@ResponseBody
    public boolean doPut(@RequestBody  TodoDto todoDto) {
        System.out.println("TodoControllr.doPut");
        System.out.println("todoDto = " + todoDto);
        return false;
    }

    // 4. [D]
    @DeleteMapping("") //@ResponseBody
    public boolean doDelete(@RequestParam int tno) {
        System.out.println("TodoControllr.doDelete");
        System.out.println("tno = " + tno);
        return false;
    }
}
