package example.객체연관관계;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class 하위클래스 {
    private String data;

    private 상위클래스 상위객체;
}
