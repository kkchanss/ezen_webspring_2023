package ezenweb.model.dto;

import ezenweb.model.entity.ProductImgEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class ProductImgDto {
    private String uuidFile; // 이미지식별이름[PK]
    private String realFileName; // 이미지실제이름

    public ProductImgEntity toProductImgEntity(){
        return ProductImgEntity.builder()
                .uuidFile(this.uuidFile)
                .realFileName(this.realFileName)
                .build();
    }
}
