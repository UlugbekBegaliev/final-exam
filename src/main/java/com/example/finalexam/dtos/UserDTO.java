package com.example.finalexam.dtos;

import com.example.finalexam.entities.User;
import lombok.*;

@Builder
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String login;
    private String password;

    public static UserDTO from(User user) {
        return builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .login(user.getLogin())
                .password(user.getPassword())
                .build();
    }
}
