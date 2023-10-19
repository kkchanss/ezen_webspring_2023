package example.task.object_relationship;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class 카테고리 {
    private int 카테고리번호;
    private String 카테고리이름;
    @Builder.Default
    private List<제품> 참조제품객체들 = new ArrayList<>();
}
