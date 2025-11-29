package com.example.store.mappers;

import com.example.store.dtos.OrderDto;
import com.example.store.orders.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
