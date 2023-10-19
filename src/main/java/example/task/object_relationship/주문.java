package example.task.object_relationship;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class 주문 {
    private int 주문번호;
    private int 주문가격;
    @ToString.Exclude
    private 제품 주문제품;
}
