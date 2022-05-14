package com.example.finalexam.services;

import com.example.finalexam.dtos.UserDTO;
import com.example.finalexam.entities.User;
import com.example.finalexam.exceptions.UserAlreadyExistsException;
import com.example.finalexam.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO register(String name, String email, String login, String password) throws UserAlreadyExistsException {

        UserDTO dto = UserDTO.builder()
                .name(name)
                .email(email)
                .login(login)
                .password(password)
                .build();

        if (userRepository.existsByEmail(dto.getEmail())){
            throw new UserAlreadyExistsException();
        }
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .login(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        userRepository.save(user);

        return UserDTO.from(user);
    }
}
