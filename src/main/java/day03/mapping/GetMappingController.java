package day03.mapping;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/day03/get")
public class GetMappingController {
    // 1. HttpServletRequest 객체 이용한 GET메소드 URL퀴리스트링의 매개변수 요청
    @GetMapping("/method1")
    public String method1(HttpServletRequest request) {
        System.out.println("GetMappingController.method1");
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        return "정상 응답";
    }
    // 2. @RequestParam URL 매개변수 쿼리스트링 [ URL?매개변수=값 ] 매개변수 매핑
    @GetMapping("/method2")
    public String method2(@RequestParam String param1) {
        System.out.println("GetMappingController.method2");
        System.out.println("param1 = " + param1);
        return "정상 응답";
    }


    // 3. @RequestParam 두 개 이상 [ 여러개 가능 ]
    @GetMapping("/method3")
    public String method3(@RequestParam String param1, @RequestParam String param2) {
        System.out.println("GetMappingController.method3");
        System.out.println("param1 = " + param1);
        System.out.println("param2 = " + param2);
        return "정상 응답";
    }

    // 4. 여러개 매개변수를 DTO로 자동 반환 매핑 
    @GetMapping("/method4")
    public String method4(ParamDto paramDto) {
        System.out.println("GetMappingController.method4");
        System.out.println("paramDto = " + paramDto);
        return "정상 응답";
    }

    // 5. 여러개 매개변수를 DTO로 자동 반환 매핑
    @GetMapping("/method5")
    public String method5(@ModelAttribute ParamDto paramDto) {
        System.out.println("GetMappingController.method5");
        System.out.println("paramDto = " + paramDto);
        return "정상 응답";
    }

    // 6. 쿼리스트링( URL?매개변수=값&매개변수=값) vs 경로매개변수 (URL/값1/값2)
    // <a href=""> </a>                         ServerSocket("URL/{매개변수}/{매개변수}")
    @GetMapping("/method6/{param1}/{param2}") // PathVariable 방식
    public String method6(@PathVariable("param1") String param1, @PathVariable("param2") int param2) {
        System.out.println("GetMappingController.method6");
        System.out.println("param1 = " + param1);
        System.out.println("param2 = " + param2);
        return "정상 응답";
    }

    // 7. 경로매개변수 방식도 DTO 지원
    @GetMapping("/method7/{param1}/{param2}") // PathVariable 방식
    public String method7(ParamDto paramDto) {
        System.out.println("GetMappingController.method7");
        System.out.println("paramDto = " + paramDto);
        return "정상 응답";
    }

    // 8. 경로매개변수 방식도 DTO 지원
    @GetMapping("/method8/{param1}/{param2}") // PathVariable 방식
    public String method8(@ModelAttribute ParamDto paramDto) {
        System.out.println("GetMappingController.method8");
        System.out.println("paramDto = " + paramDto);
        return "정상 응답";
    }

    // 함수의 반환타입이 String인 이유 : @ResponseBody가 자동으로 반환타입이 String이면 resp.setContentType("text/html; charset=utf-8");
    // return "정상 응답";인 이유 : @ResponseBody가 자동으로 resp.getWriter().println("정상 응답"); 제공
}
