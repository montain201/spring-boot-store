package com.example.store.products;

import com.example.store.dtos.RegisterProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(RegisterProductRequest productDto);
    ProductDto toDto(Product product);
    @Mapping(target="id", ignore = true)
    void update(RegisterProductRequest request, @MappingTarget Product product);
}
