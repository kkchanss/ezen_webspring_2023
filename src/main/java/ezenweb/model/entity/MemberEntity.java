package ezenweb.model.entity;

import ezenweb.model.dto.MemberDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스를 db테이블과 매핑 [ 엔티티 클래스 <----> db 테이블 ( 엔티티 객체  1개 <----> db테이블 내 레코드)]
@Table(name = "member") // DB테이블명 정의 [ 생략시 해당 클래스명이 db테이블명으로 자동 생성 ]
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class MemberEntity extends BaseTime{
    @Id // 해당 필드를 pk로 선정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int mno;            // 1. 회원번호
    @Column(name = "memail", length = 50, nullable = false, unique = true) // 해당 필드 선정 [ 속성 ] name = "필드명"
    private String memail;      // 2. 이메일 [ 회원아이디 객체 ]
    @Column(length = 30, nullable = false) // 해당 필드 선정
    private String mpassword;   // 3. 비밀번호
    @Column(length = 20, nullable = false) // 해당 필드 선정
    private String mname;       // 4. 이름
    @Column(length = 14, nullable = false, unique = true) // 해당 필드 선정
    private String mphone;      // 5. 연락처
    @Column // 해당 필드 선정
    @ColumnDefault("'user'") // ColumnDefault("초기값") ColumnDefault("'문자일경우'")
    private String mrole;       // 6. 회원 등급 ( 일반 회원 = user, 관리자 회원 = admin )
    // [ BaseTime 클래스가 상속해주는 필드 : 1. 회원가입일자 2. 회원정보수정일 ]

    // 게시물 목록 = 내가 쓴 게시물
    @Builder.Default // 빌더패턴 사용시 해당 필드를 값을 기본값으로 사용
    @OneToMany(mappedBy ="memberEntity") // 하나가 다수에게 [ PK ] // 실제 DB 영향 x
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    public MemberDto toDto() {
        return MemberDto.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mrole(this.mrole)
                .mphone(this.mphone)
                .mname(this.mname)
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}
