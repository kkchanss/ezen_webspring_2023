package example.task.object_relationship;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString @Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int one;
    private int oprice;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "pno")
    private ProductEntity productEntity;
}
