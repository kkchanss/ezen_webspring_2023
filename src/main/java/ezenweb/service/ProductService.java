package ezenweb.service;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.repository.ProductCategoryEntityRepository;
import ezenweb.model.repository.ProductEntityRepository;
import ezenweb.model.repository.ProductImgEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductCategoryEntityRepository productCategoryEntityRepository;

    @Autowired
    private ProductEntityRepository productEntityRepository;

    @Autowired
    private ProductImgEntityRepository productImgEntityRepository;

    @Autowired
    private FileService fileService;
    // ==================================== 카테고리 =================================
    // 1. 카테고리 등록
    @Transactional
    public boolean addCategory(ProductCategoryDto productCategoryDto) {
        return productCategoryEntityRepository.save(productCategoryDto.toProductCategoryEntity()).getPcno()>=1;

    }
    // 2. 카테고리 출력
    @Transactional
    public List<ProductCategoryDto> printCategory(){
        List<ProductCategoryEntity> productCategoryEntities = productCategoryEntityRepository.findAll();
        List<ProductCategoryDto> result = new ArrayList<>();

/*        for(ProductCategoryEntity productCategoryEntity : productCategoryEntities){
            result.add(productCategoryEntity.toProductCategoryDto());
        }
        return result;*/
        return productCategoryEntityRepository.findAll().stream().map(
                e->{return e.toProductCategoryDto();}
        ).collect(Collectors.toList());
    }
    // 3. 카테고리 수정
    @Transactional
    public boolean updateCategory(ProductCategoryDto productCategoryDto) {
        // 1. 수정할 엔티티 찾는다 [pcno] // 2. 찾은 엔티티가 존재하면 수정o 아니면 수정x // 3. 성공시 true / 실패시 false
        ProductCategoryEntity productCategoryEntity = toEntity(productCategoryDto.getPcno());
        if(productCategoryEntity != null) {
            productCategoryEntity.setPcname(productCategoryDto.getPcname());
            return true;
        }
        return false;
    }
    // 4. 카테고리 삭제
    @Transactional
    public boolean deleteCategory(int pcno) {
        // 1. 삭제할 엔티티 찾는다 [pcno] // 2. 찾은 엔티티가 존재하면 삭제o 아니면 삭제x // 3. 성공시 true / 실패시 false
        ProductCategoryEntity productCategoryEntity = toEntity(pcno);
        if(productCategoryEntity != null) {
            productCategoryEntityRepository.delete(productCategoryEntity);
            return true;
        }
        return false;
    }

    // 5. 부가적인 엔티티검색용 함수
    public ProductCategoryEntity toEntity(int pcno) {
        Optional<ProductCategoryEntity> productCategoryEntityOptional = productCategoryEntityRepository.findById(pcno);
        return productCategoryEntityOptional.isPresent() ? productCategoryEntityOptional.get() : null;
    }
}
