package com.example.store.payments;

import com.example.store.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public class PaymentResult {
    private Long orderId;
    private OrderStatus paymentStatus;
}
