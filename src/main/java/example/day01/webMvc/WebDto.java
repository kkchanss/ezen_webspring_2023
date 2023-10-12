package example.day01.webMvc;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor@AllArgsConstructor
@ToString @Builder
public class WebDto {

    private int tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
}
