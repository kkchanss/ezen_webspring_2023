package example.day01.webMvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // springboot 의존성 [ MVC, RESTful, 내장톰켓 등등 지원 ]
public class WebStart {
    public static void main(String[] args) {
        SpringApplication.run(WebStart.class); // spring 시작
    }

}

/*
// syso : 이클립스 자동완성
// sout
        System.out.println("sout -> println");
                System.out.printf("souf -> printf");
                System.out.println("consoleStart.main"); // soutm : 현재 실행중인 함수명 호출
                System.out.println("args = " + Arrays.toString(args)); // soutm 현재 실행주인 함수의 매개변수 호출
                System.out.println("args = " + args); // soutv 변수 출력
// ctrl + shift + f10 또는 왼쪽에 실행 화살표*/
