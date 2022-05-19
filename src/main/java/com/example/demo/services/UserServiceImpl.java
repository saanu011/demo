package com.example.demo.services;

import com.example.demo.repositories.UserRepository;
import com.example.demo.models.User;
import com.example.demo.models.dto.UserDto;
import com.example.demo.utils.BeanMapper;
import com.example.demo.utils.CommonUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public Page<UserDto> index(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::convertUserToDto);
    }
    public UserDto create(UserDto userDto) {
        User user = new User();
        CommonUtil.copyNonNullProperties(userDto, user);
        userRepository.save(user);
        return convertUserToDto(user);
    }

    private UserDto convertUserToDto(User user) {
        BeanMapper mapper = new BeanMapper();
        return mapper.map(user, UserDto.class);
    }
}
