package example.day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller // 해당 클래스를 스프링mvc중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController2 {
    // 1. GET
    @RequestMapping(value = "/day03/orange", method= RequestMethod.GET)
    @ResponseBody
    public String getOrange(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 2. POST
    @RequestMapping(value = "/day03/orange", method= RequestMethod.POST)
    @ResponseBody
    public String postOrange(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 3. PUT
    @RequestMapping(value = "/day03/orange", method= RequestMethod.PUT)
    @ResponseBody
    public String putOrange(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }

    // 4. DELETE
    @RequestMapping(value = "/day03/orange", method= RequestMethod.DELETE)
    @ResponseBody
    public String deleteOrange(HttpServletRequest request) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        return "정상 응답";
    }
}

/*
    @RepuestMapping : 해당 클래스내 메소드 매핑URL들의 공통 URL 정의
    @Controller     : -ResponseBody 미포함
    @RestController : +ResponseBody 포함 (해당 클래스내 모든 함수들은 자동으로 @ResponseBody 주입)

        - 클래스 위에 선언
        - 스프링 MVC중에서 controller 역할
        - 서블릿 기능을 제공 받음.
        - 컨트롤러 클래스 안에서 주요 사용되는 어노테이션

            1-1. @RequestMapping
                - 외부 HTTP 요청으로부터 해당 함수와 매칭을 연결해주는 어노테이션
                - @RequestMapping(value = "매핑할 URL정의", method=RequestMethod.(HTTP함수명)
                    - 프로잭트 내 절대적으로 동일한 *매핑URL과 HTTP함수 모두 동일하게 정의할 수 없다.
                    - 매핑URL : 본인 임의로 URL 주소 만들기
                - @

            1-2 XXXMapping
                - 외부 HTTP 요청으로부터 해당 함수와 매칭을 연결해주는 어노테이션 ( @RequestMapping 어노테이션에서 세분화된 어노테이션)
                @GetMapping("/example.day03/blue") <=>  @RequestMapping(value = "/example.day03/blue", method= RequestMethod.GET)
                @PostMapping("/example.day03/blue") <=>  @RequestMapping(value = "/example.day03/blue", method= RequestMethod.POST)
                @PutMapping("/example.day03/blue") <=>  @RequestMapping(value = "/example.day03/blue", method= RequestMethod.PUT)
                @DeleteMapping("/example.day03/blue") <=>  @RequestMapping(value = "/example.day03/blue", method= RequestMethod.DELETE)

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

            3. 매개변수 요청하는 방법
                1. HttpServletRequest request
                    String param = request.getParameter("매개변수명");
                2. @RequestParam 해당 매개변수를 매핑해서 함수 매개변수(인수)에 대입
                    @RequestParam String param
                    * 여러번 선언 가능

                    1. 쿼리스트링 방식 요청
                        http://localhost:8080/day03/get/method4?param1=유재석&param2=50
                        @RequestParam
                    2. 경로매개변수 방식 요청
                        http://localhost:8080/day03/get/method4/유재석/50
                        * URL경로상의 매개변수명이 없으므로 @PathVariable() 순서대로 매개변수 정의
                        @PathVariable("경로순서매개변수명")
                3. DTO 요청 매핑
                    [ 조건 ]
                    1. 해당 DTO는 getter, setter 메소드가 존재할 경우
                    2. 매개변수와 DTO의 필드명이 일치
                    3. @RequestParam 사용안함

                    방법1 :
                        @GetMapping("/method4")
                        public String method4(ParamDto paramDto){}

                    방법2 :
                         @GetMapping("/method5")
                         public String method5(@ModelAttribute ParamDto paramDto){}
            4.
                HTTP BODY 사용 하는 메소드
                    @PostMapping("")
                    @PutMapping("")
                HTTP BODY 사용 하지 않는 메소드
                    @GetMapping("")
                    @DeleteMapping("")
            5. @PostMapping / @PutMapping
                BODY 요청 타입
                    1. application/json
                        @RequestBody : 매개변수들이 JSON 형식일때 [ *일반적인 ]
                        1. 폼전송 아닐때
                            @RequestBody ParamDto paramDto
                        2. ajax
                            $.ajax({
                                url : "",
                                method : "",
                                data : {},
                            })
                    2. application/x-www-form-urlencoded
                        @RequestParam : 매개변수들이 폼 형식일때 [ *첨부파일전송 ]
                        1. 폼전송 [ *주로 첨부파일 있을때 ]
                        ParamDto paramDto
                        2. ajax
                            $.ajax({

                            })
 */