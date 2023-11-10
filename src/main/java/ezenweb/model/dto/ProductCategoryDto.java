package ezenweb.model.dto;

import ezenweb.model.entity.ProductCategoryEntity;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class ProductCategoryDto {
    private int pcno; // 카테고리 번호 [PK]
    private String pcname; // 카테고리 이름

    public ProductCategoryEntity toProductCategoryEntity() {
        return ProductCategoryEntity.builder()
                .pcno(this.pcno)
                .pcname(this.pcname)
                .build();
    }
}
