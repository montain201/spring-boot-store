package com.example.store.mappers;


import com.example.store.dtos.RegisterUserRequest;
import com.example.store.dtos.UpdateUserRequest;
import com.example.store.dtos.UserDto;
import com.example.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
