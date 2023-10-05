package day04;
/* 메타 어노테이션? : 실행 또는 컴파일 했을때 사용방법 (이미 설치된 라이브러리)에 대해 정의 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
        System.out.println("출력");
    }
}

/*
    스프링이 정적(view) 파일들을 찾는 위치 resources폴더
        - 주의할점 : 본인이 만들고 싶은 곳에 정적(view) 파일 만들면 안된다.
    HTML : resources -> templates -> html 파일
    JS/CSS/Image : resources -> static -> JS/CSS/Image 파일

    - JSP 프로젝트와  SPRING 프로젝트의 정적파일 경로 차이
        - JSP는 패키지와
        - SPRING : resources -> static -> js/css/image 파일

 */


/*
    다른 클래스를 호출하는 방법
        1. 메소드를 호출하고자하는 클래스의 객체 이용
            클래스명 객체명 = new 클래스명()
            객체명.메소드명();
        2. 지역변수 바로 메소드 일회성 호출
            new 클래스명().메소드명();
        3. 싱글톤 사용 [*단 해당 클래스가 싱글톤 구현되어야 가능]
            클래스명.getInstance().메소드명()
        4. 메소드가 싱글톤이 아니면 단순 static 메소드이면
            클래스명.메소드명();
        +++++++++++++++++++++++++++++++++++++++++++++++
        스프링 컨테이너[스프링 객체들을 관리하는 메모리 공간]에 저장된 빈 [객체] 사용.
            - 전제조건 : 스프링이 빈 등록이 되어 있어야 한다.
            1. 빈 등록하는 방법
                1. 해당 클래스 위에 MVC 관련 어노테이션
                    @RestController @Service @Repository @Controller 등등
                2. 그외 일반적으로 개발자가 만든 클래스를 빈 등록
                    @Component
        5. 스프링 빈 등록 객체의 메소드를 호출할때
               @Autowired : 의존성주입 [ 스프링 미리 등록된 컨테이너 빈 찾아서 주입 ]
               클래스명 객체명

               객체명.메소드명();
 */