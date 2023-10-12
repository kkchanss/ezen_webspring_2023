package example.day03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
    }
}


/*
    @ : 어노테이션
        표준 어노테이션 : 기본적으로 제공하는 어노테이션 ex) @Ovrride
        메타 어노테이션 : 메타 데이터를 제공하는 어노테이션
        - 메타 데이터: 컴파일 과정과 실행 과정에서 코드를 어떻게 컴파일하고 처리할 것인지 알려주는 정보
        - 스프링 프레임워크를 사용하는데 있어서 매우 중요
        
    @SpringBootApplicaion
    
        - Spring MVC 지원, Restful (get,post,put,delete 함수), 톰캣(내장) 등등 제공
    
        - 주요 어노테이션
        1. @ComponentScan : 컴포넌트 스캔(검색)해서 빈 (스프링 컨테이너 = 스프링이 객체들을 관리하는 메모리공간) 등록
            @Configuration @Repository @Controller @RestController 등등
        2. @EnableAutoConfiguration
        3. @SpringBootConfiguration

        - 스프링부트 시작
            * main함수 (main스레드) 안에서 작성
            SpringApplication.run(현재클래스명.class);

        - 주의할점
            @SpringBootApplication을 사용하는 해당 클래스는 항상 최상위 위치에 생성

        - 내장 톰캣 사용시 port 변경
            프로젝트 폴더 -> SRC -> MAIN -> resources
            1. 파일생성
            2. 파일명 : application.properties
                - 스프링에서의 외부설정을 등록하는 곳
                - 파일명에 오타가 있을 경우 스프링이 해당 파일을 못 찾아서 설정이 등록 안됨

    @ServletComponentScan
       - 서블릿 스캔(검색)해서 스프링 빈에 등록
       - @Controller 어노테이션 사용하므로 사용빈도 적다.

    HTTP 함수
        GET,POST
        Header : 편지봉투
            get, delete, post, put
            - 보안 취약
            - 쿼리스트링 방식
        BODY : 편지의 내용물
            post, put
            - 보안 안전

    깃 커밋
    메뉴 -> VCS ->
 */