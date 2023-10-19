package example.task.object_relationship;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pno;
    private String pname;
    @ToString.Exclude
    @JoinColumn(name = "cno")
    @ManyToOne
    private CategoryEntity categoryEntity;
    @Builder.Default
    @OneToMany(mappedBy = "productEntity")
    private List<OrderEntity> orderEntityList = new ArrayList<>();
}
