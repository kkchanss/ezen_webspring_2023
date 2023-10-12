package example.day03.restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller // 해당 클래스를 스프링mvc중 컨트롤러 객체로 사용 // 스프링 컨트롤러 객체를 빈에 등록
public class RestController1 {
    // 1. GET
    @RequestMapping(value = "/day03/black", method= RequestMethod.GET)
    public void getBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("정상 응답");
    }

    // 2. POST
    @RequestMapping(value = "/day03/black", method= RequestMethod.POST)
    public void postBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("정상 응답");
    }

    // 3. PUT
    @RequestMapping(value = "/day03/black", method= RequestMethod.PUT)
    public void putBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("정상 응답");
    }

    // 4. DELETE
    @RequestMapping(value = "/day03/black", method= RequestMethod.DELETE)
    public void deleteBlack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 요청
        String param1 = request.getParameter("param1");
        System.out.println("param1 = " + param1);
        // 2. 통신
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("정상 응답");
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


 */