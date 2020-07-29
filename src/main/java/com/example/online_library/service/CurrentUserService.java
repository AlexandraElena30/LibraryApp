package com.example.online_library.service;


import com.example.online_library.domain.model.UsersDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@AllArgsConstructor
@Validated
public class CurrentUserService {

    public static UsersDTO currentUser;
    public static int logged = 0;

}
