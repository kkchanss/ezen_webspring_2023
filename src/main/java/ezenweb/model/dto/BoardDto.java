package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import java.time.LocalDateTime;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString @Builder
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private String bfile;
    private int mno;
    // +
    private LocalDateTime cdate;
    private LocalDateTime udate;

    // dto -> entity
    public BoardEntity saveToEntity() {
        return BoardEntity.builder()
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bfile(this.bfile)
                .mno(this.mno)
                .build();
    }

}
