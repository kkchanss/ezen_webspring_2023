package ezenweb.model.dto;

import ezenweb.model.entity.ProductEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder @ToString
public class ProductDto {
    private String pno; // 제품번호
    private String pname; // 제품명
    private String pcomment; // 제품설명
    private int pprice; // 제품가격
    private byte pstate; // 제품상태 [ 0: 판매중, 1: 판매중지, 2:재고없음, 3:폐기 등등]
    private int pstock; // 제품 재고

    // ========== 등록용 ========== //
    // + 첨부파일이 여러개일때 [ vs 첨부파일 하나일때 = boardDto ]
    private List<MultipartFile> fileList;
        // <input type="file" name="fileList" multiple>

    private int pcno; // 카테고리 번호

    // ========== 출력용 ========== //
    private ProductCategoryDto categoryDto;
    private List<ProductImgDto> imgList;

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
