package example.task;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class PhoneDto {

    private int pno;
    private String pname;
    private String pphone;

}
