package example.task.Car;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor@NoArgsConstructor
@Setter@Getter@ToString@Builder
public class CarDto {
    private int cno;
    private String cid;
    private String ctype;
    private LocalDateTime cdate;

    public CarEntity toEntity(){
        return CarEntity.builder()
                .cno(this.cno)
                .cid(this.cid)
                .ctype(this.ctype)
                .cdate(this.cdate)
                .build();
    }

}