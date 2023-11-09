package ezenweb.model.entity;

import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name ="product")
public class ProductEntity { /* 제품 테이블 */

    @Id private String pno; // 제품번호 [PK]
    @Column private String pname; // 제품명
    @Column(columnDefinition = "LONGTEXT") private String pcomment; // 제품설명
    @Column private int pprice; // 제품가격
    @Column@ColumnDefault("0") private byte pstate; // 제품상태 [ 0: 판매중, 1: 판매중지, 2:재고없음, 3:폐기 등등]
    @Column@ColumnDefault("0") private int pstock; // 제품 재고

    // FK 만들기
    @ToString.Exclude
    @JoinColumn(name ="pcno")
    @ManyToOne
    private ProductCategoryEntity productCategoryEntity;
    // @JoinColumn(name ="fk필드명[아무거나]")

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productEntity", cascade = CascadeType.ALL) // mappedBy 안하면 DB에서 서로 연결해주는 테이블 하나 더 생성하게 됨
    private List<ProductImgEntity> productImgEntities = new ArrayList<>();
    // OneToMany(mappedBy = "fk 사용중인 엔티티클래스 필드명")
}
