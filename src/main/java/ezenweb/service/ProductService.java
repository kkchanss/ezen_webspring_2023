package ezenweb.service;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.model.dto.ProductImgDto;
import ezenweb.model.entity.ProductCategoryEntity;
import ezenweb.model.entity.ProductEntity;
import ezenweb.model.entity.ProductImgEntity;
import ezenweb.model.repository.ProductCategoryEntityRepository;
import ezenweb.model.repository.ProductEntityRepository;
import ezenweb.model.repository.ProductImgEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // ==================================== 상품 =================================
    // 1. 상품 등록
    @Transactional public boolean addProduct(ProductDto productDto) {
        System.out.println(productDto);

        // 1. 카테고리 엔티티 준비
        ProductCategoryEntity productCategoryEntity = productCategoryEntityRepository.findById(productDto.getPcno()).get();
        // 2. 제품 엔티티를 생성
            // fk 방향 : 제품 엔티티에 카테고리 엔티티 넣어주기
            // pk 방향 : 제품 엔티티에 이미지 엔티티 리스트 넣어주기
            // 2-1 제품 엔티티 생성 [ toEntity 메소드 구현 없이 ]
        ProductEntity productEntity = ProductEntity.builder()
                .pno(productCategoryEntity.getPcno() + "-" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))) // auto_increment가 아니므로 수동 pk 만들어주기 1. uuid 2. 날짜조합
                    // 카테고리번호 - 등록날짜
                .pname(productDto.getPname())
                .pcomment(productDto.getPcomment())
                .pprice(productDto.getPprice())
                .pstock(productDto.getPstock())
                .productCategoryEntity(productCategoryEntity)
                .productImgEntities(new ArrayList<>())
                .build();
            // 2-2 제품 이미지 등록 [ 첨부파일 여러개 ]
        productDto.getFileList().stream().map((file) -> {
            return fileService.fileUpload(file);
        }).collect(Collectors.toList()) // * 업로드 된 식별파일명 반환되는데.. map 이니깐 리스트로 반환
                .forEach(uuidFile -> {
                    productEntity.getProductImgEntities().add(
                            ProductImgEntity.builder()
                                    .uuidFile(uuidFile)
                                    .realFileName(uuidFile.split("_")[1])
                                    .productEntity(productEntity)
                                    .build()
                    );
                });
        // 3. 제품 등록
        productEntityRepository.save(productEntity);
        return true;

    }
    // 2. 제품 출력
    @Transactional public List<ProductDto> onProductAll() {

        // 1. 모든 제품의 엔티티 호출 // JPA 정렬 : Sort.by(Sort.Direction.DESC, "필드명")
        List<ProductEntity> productEntityList = productEntityRepository.findAll(Sort.by(Sort.Direction.DESC, "cdate"));
        // 2. 모든 제품의 entity -> dto로 변환해서 반환하기
        return productEntityList.stream().map((p)-> {
            return ProductDto.builder()
                    .pno(p.getPno())
                    .pstock(p.getPstock())
                    .pstate(p.getPstate())
                    .pname(p.getPname())
                    .pcomment(p.getPcomment())
                    .pprice(p.getPprice())
                    .categoryDto(
                            ProductCategoryDto.builder()
                                    .pcno(p.getProductCategoryEntity().getPcno())
                                    .pcname(p.getProductCategoryEntity().getPcname())
                                    .build()
                    )
                    .imgList(
                            p.getProductImgEntities().stream().map((img)-> {
                                return ProductImgDto.builder()
                                        .realFileName(img.getRealFileName())
                                        .uuidFile(img.getUuidFile())
                                        .build();
                            }).collect(Collectors.toList())
                    )
                    .build();
        }).collect(Collectors.toList());
    }
    // 3. 상품 수정
    @Transactional public boolean updateProduct(ProductDto productDto) {
        return false;
    }
    // 4. 상품 삭제
    @Transactional public boolean deleteProduct(String pno) {
        return false;
    }
}
