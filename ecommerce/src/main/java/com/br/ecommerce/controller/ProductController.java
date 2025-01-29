package com.br.ecommerce.controller;

import com.br.ecommerce.dto.product.CreateProductDto;
import com.br.ecommerce.dto.product.ResponseCategoryDto;
import com.br.ecommerce.dto.product.ResponseProductDto;
import com.br.ecommerce.dto.product.UpdateProductDto;
import com.br.ecommerce.entity.Category;
import com.br.ecommerce.entity.Product;
import com.br.ecommerce.service.CategoryService;
import com.br.ecommerce.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDto>> listProducts() {
        return ResponseEntity.ok(productService.getProducts().stream().map(ResponseProductDto::new).toList());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody @Validated CreateProductDto productDto) {
        Product createdProduct = productService.createProduct(productDto);
        String location = "/products/" + createdProduct.getId();
        return ResponseEntity.created(URI.create(location))
                .body(new ResponseProductDto(createdProduct));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseProductDto> updateProduct(@PathVariable("id") Long id,
                                           @RequestBody UpdateProductDto updateProductDto) {
        var product = productService.updateProduct(id, updateProductDto);
        return ResponseEntity.ok(new ResponseProductDto(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category")
    public ResponseEntity<List<ResponseCategoryDto>> listCategory(){
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories.stream().map(ResponseCategoryDto::new).toList());
    }
 }
