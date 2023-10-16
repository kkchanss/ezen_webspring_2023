package example.day06;

import lombok.*;

import java.time.LocalDateTime;

// Dto 사용처 :
    // AJAX(JSON/TEXT/FORM) -- DTO --> Controller
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class NoteDto {
    private int no; // 게시물 번호
    private String title; // 게시물 내용
    private String writer; // 작성자
    private String password; // 패스워드
    private LocalDateTime date; // 작성일

    // + DB에는 없지만 필요한 코드

    // * dto를 엔티티로 변환해주는 함수 [ service]
    public NoteEntity toEntity() {
        return NoteEntity.builder()
                .no(this.no)
                .title(this.title)
                .writer(this.writer)
                .password(this.password)
                .date(this.date)
                .build();
    }
}
