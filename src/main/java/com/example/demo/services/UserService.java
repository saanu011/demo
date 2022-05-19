package com.example.demo.services;

import com.example.demo.models.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface UserService {

    Page<UserDto> index(Pageable pageable);
    UserDto create(UserDto userDto);
}
