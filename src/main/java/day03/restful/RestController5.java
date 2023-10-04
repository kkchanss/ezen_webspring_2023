package day03.restful;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController // @Controller 동일한 기능 + @ResponseBody 제공
@RequestMapping(value="/day03") // 클래스에 매핑 URL 정의할 경우. 해당 클래스 안에 메소드들의 공통된 URL
public class RestController5 {
    // 1. GET
    @GetMapping(value = "/pink")
    public String getPink(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 2. POST
    @PostMapping(value = "/pink")
    public String postPink(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 3. PUT
    @PutMapping(value = "/pink")
    public String putPink(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 4. DELETE
    @DeleteMapping(value = "/pink")
    public String deletePink(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }
}

/*
    @Controller
        - 클래스 위에 선언
        - 스프링 MVC중에서 controller 역할
        - 서블릿 기능을 제공 받음.
        - 컨트롤러 클래스 안에서 주요 사용되는 메소드
            1. @RequestMapping
                - 외부 HTTP 요청으로부터 해당 함수와 매칭을 연결해주는 어노테이션
                - @RequestMapping(value = "매핑할 URL정의", method=RequestMethod.(HTTP함수명)
                    - 프로잭트 내 절대적으로 동일한 *매핑URL과 HTTP함수 모두 동일하게 정의할 수 없다.

            2. @ResponseBody
                - 외부 HTTP 요청에 따른 응답을 제공하는 어노테이션
                - HTTPServletResponse response 응답객체 대신 사용
                - 해당 함수의 반환타입에 따른 응답 타입을 자동 설정
                    response.setContentType -> 해당 함수의 반환타입
                    Strinng                 -> "text/html; charset=utf-8"
                    Dto                     -> "application/json; charset=utf-8"
                    Map                     -> "application/json; charset=utf-8"
                    List                    -> "application/json; charset=utf-8"
                - return에 응답할 데이터

                response.setContentType("text/html; charset=utf-8");

 */