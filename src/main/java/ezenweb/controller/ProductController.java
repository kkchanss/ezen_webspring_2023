package ezenweb.controller;

import ezenweb.model.dto.ProductCategoryDto;
import ezenweb.model.dto.ProductDto;
import ezenweb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    // ==================================== 카테고리 =================================
    // 1.카테고리 등록
    @PostMapping("/category")
    public boolean addCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return productService.addCategory(productCategoryDto);
    }
    // 2. 카테고리 출력
    @GetMapping("/category")
    public List<ProductCategoryDto> printCategory() {
        return productService.printCategory();
    }
    // 3. 카테고리 수정
    @PutMapping("/category")
    public boolean updateCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return productService.updateCategory(productCategoryDto);
    }
    // 4. 카테고리 삭제
    @DeleteMapping("/category")
    public boolean deleteCategory(@RequestParam int pcno) {
        return productService.deleteCategory(pcno);
    }

    // =================================== 상품 ========================================
    // 1. 상품 등록
    @PostMapping("")
    public boolean addProduct(ProductDto productDto) {
        return productService.addProduct(productDto);
    }
    // 2. 상품 출력
    @GetMapping("")
    public List<ProductDto> printProduct(){
        return productService.onProductAll();
    }
    // 3. 상품 수정
    @PutMapping("")
    public boolean  updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }
    // 4. 상품 삭제
    @DeleteMapping("")
    public boolean deleteProduct(@RequestParam String pno) {
        return productService.deleteProduct(pno);
    }


}
