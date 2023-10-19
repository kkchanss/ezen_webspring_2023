package example.task.object_relationship;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class 제품 {
    private int 제품번호;
    private String 제품이름;
    @ToString.Exclude
    private 카테고리 제품카테고리;
    @Builder.Default
    private List<주문> 참조주문객체들 = new ArrayList<>();
}
