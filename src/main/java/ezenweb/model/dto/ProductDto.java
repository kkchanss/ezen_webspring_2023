package ezenweb.model.dto;

import ezenweb.model.entity.ProductEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Id;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class ProductDto {
    private String pno; // 제품번호
    private String pname; // 제품명
    private String pcomment; // 제품설명
    private int pprice; // 제품가격
    private byte pstate; // 제품상태 [ 0: 판매중, 1: 판매중지, 2:재고없음, 3:폐기 등등]
    private int pstock;

    public ProductEntity toProductEntity() {
        return ProductEntity.builder()
                .pno(this.pno)
                .pname(this.pname)
                .pcomment(this.pcomment)
                .pprice(this.pprice)
                .pstate(this.pstate)
                .pstock(this.pstock)
                .build();
    }
}
