package day01.lombok;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor // 빈생성자 자동 생성
@AllArgsConstructor // 풀 생성자 자동 생성
@Getter @Setter // getter, sestter 메소드 자동생성
@ToString // toString 메소드 자동생성
@Builder // 빌더 패턴
public class LombokDto {
    private int tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
