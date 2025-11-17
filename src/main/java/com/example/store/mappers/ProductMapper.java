package com.example.store.mappers;

import com.example.store.dtos.ProductDto;
import com.example.store.dtos.RegisterProductRequest;
import com.example.store.dtos.UpdateUserRequest;
import com.example.store.entities.Product;
import com.example.store.entities.User;
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
