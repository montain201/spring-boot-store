package com.example.store.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private double price;
    private String description;
}
