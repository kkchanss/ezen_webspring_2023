package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import java.time.LocalDateTime;


@NoArgsConstructor @AllArgsConstructor
@Builder @ToString
@Getter @Setter
public class MemberDto {
    private int mno;            // 1. 회원번호
    private String memail;      // 2. 이메일 [ 회원아이디 객체 ]
    private String mpassword;   // 3. 비밀번호
    private String mname;       // 4. 이름
    private String mphone;      // 5. 연락처
    private String mrole;       // 6. 회원 등급 ( 일반 회원 = user, 관리자 회원 = admin )

    // + baseTime
    private LocalDateTime cdate;
    private LocalDateTime udate;


    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrole(this.mrole)
                .mphone(this.mphone)
                .mname(this.mname)
                .build();
    }
}
