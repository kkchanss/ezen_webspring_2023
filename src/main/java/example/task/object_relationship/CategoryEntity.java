package example.task.object_relationship;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno;
    private String cname;
    @Builder.Default
    @OneToMany(mappedBy = "categoryEntity")
    private List<ProductEntity> productEntityList = new ArrayList<>();
}
