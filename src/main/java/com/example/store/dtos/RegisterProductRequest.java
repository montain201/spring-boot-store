package com.example.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterProductRequest {

    private String name;
    private double price;
    private String description;
    private byte categoryId;
}
