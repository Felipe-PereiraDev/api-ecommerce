package com.br.ecommerce.service;

import com.br.ecommerce.dto.product.CreateProductDto;
import com.br.ecommerce.dto.product.UpdateProductDto;
import com.br.ecommerce.entity.Category;
import com.br.ecommerce.entity.Product;
import com.br.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }


    public Product createProduct(CreateProductDto data){
        Category category = categoryService.findById(data.categoryId());
        Product newProduct = new Product(data, category);
        try {
            return productRepository.save(newProduct);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "o Produto \""+ data.name() +"\"já foi cadastrado");
        }
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Transactional
    public Product updateProduct(Long id, UpdateProductDto updateProduct) {
        Product product = findById(id);
        if (updateProduct.categoryId() != null) {
            Category category = categoryService.findById(updateProduct.categoryId());
            product.updateProduct(updateProduct, category);
        } else {
            product.updateProduct(updateProduct, null);
        }
        productRepository.save(product);
        return product;
    }





}
