package day02.servlet;

import lombok.*;

import java.time.LocalDate;

// 롬복 라이브러리 설치 되었다는 가정하에
@Getter // 각 필드별 get메소드 자동 생성
@Setter // 각 피드별 set메소드 자동 생성
@ToString() // 객체의 필드 정보를 출력하는 toString 메소드 자동생성
@NoArgsConstructor // 빈생성자
@AllArgsConstructor // 풀생성자
@Builder
public class TodoDto {
    
    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
