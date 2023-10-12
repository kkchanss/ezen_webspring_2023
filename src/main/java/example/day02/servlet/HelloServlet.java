package example.day02.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/hello-servlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    // 오른쪽클릭 -> 생성 -> 오버라이딩 메소드
    // -- 서블릿의 라이브 사이클 [ 생명주기 : 서블릿 객체가 생성되는 실행되는 제거 되기까지 ]
        // init(), doGet(), service(), destroy() : 개발자가 아닌 서블릿 컨테이너가 서블릿들을 관리하면서 호출
    private String message;

    // 1. 최초로 해당 서블릿의 URL 요청 했을때 1번 실행 [ 다음 요청 부터는 동일한 서블릿객체 사용하므로 ]
    @Override
    public void init() throws ServletException {
        System.out.println("HelloServlet.init");
        message = "서블릿 객체 탄생";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.doGet");
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().println(message);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet.service"); // soutm
        super.service(req,resp);
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet.destroy");
    }



}
