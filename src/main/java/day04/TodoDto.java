package day04;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
@Builder
public class TodoDto {
    private int tno;
    private String tcontent;
    private String tstate;
}
