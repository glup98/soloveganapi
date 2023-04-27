package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.UserDto;
import com.augusto.soloveganbusiness.models.User;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper implements IMapper<UserDto, User> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
