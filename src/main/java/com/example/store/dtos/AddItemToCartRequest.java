package com.example.store.dtos;

import lombok.Data;

import java.util.UUID;
@Data
public class AddItemToCartRequest {
    private Long productId;
}
