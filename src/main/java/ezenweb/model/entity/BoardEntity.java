package ezenweb.model.entity;

import ezenweb.model.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

// ------------------------ 1. 엔티티를 이용한 테이블 생성할 때 -------------------
@Entity // 해당 클래스를 엔티티로 사용
@Table(name = "board") // 테이블명 설정
@DynamicInsert // @ColumnDefault가 적용될 수 있도록 해주는 어노테이션
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardEntity extends BaseTime{ // 테이블 설계

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 필드 설계
    private int bno;
    @Column(name = "btitle", length = 200, nullable = false)
    private String btitle;
    @Column(columnDefinition = "longtext")
    private String bcontent;
    @Column
    @ColumnDefault("0")
    private int bview;
    @Column(columnDefinition = "longtext")
    private String bfile;

    @ToString.Exclude // toString() 함수에서 제외되는 필드
    @JoinColumn(name = "mno") // FK 필드로 사용 (name="FK필드명")
    @ManyToOne // 다수가 하나에게 [ FK ]
    private MemberEntity memberEntity;

    public BoardDto saveToDto() {
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bfile(this.bfile)
                .bview(this.bview)
                .mno(this.memberEntity.getMno())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}


