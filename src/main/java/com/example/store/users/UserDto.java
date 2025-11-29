package com.example.store.users;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    //@JsonIgnore
    //@JsonProperty("UserId")
    private Long id;
    private String name;
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    /*@JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;*/

}
