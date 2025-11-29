package com.example.store.products;

import com.example.store.dtos.RegisterProductRequest;
import com.example.store.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody RegisterProductRequest request,
            UriComponentsBuilder uriBuilder) {

        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        if(category == null)
          return ResponseEntity.badRequest().build();

        var product = productMapper.toEntity(request);

            product.setCategory(category);
        productRepository.save(product);

        var productDto = productMapper.toDto(product);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(productDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable(name="id") Long id,
            @RequestBody RegisterProductRequest request)
    {
        var product = productRepository.findById(id).orElse(null);
        if(product == null)
            return ResponseEntity.notFound().build();

        productMapper.update(request, product);
        var category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        if(category == null)
            return ResponseEntity.badRequest().build();
        product.setCategory(category);
        productRepository.save(product);
        return ResponseEntity.ok(productMapper.toDto(product));
    }


}
