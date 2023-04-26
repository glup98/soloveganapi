package com.augusto.soloveganbusiness.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.augusto.soloveganbusiness.dto.UserDto;
import com.augusto.soloveganbusiness.models.User;

@Component
public class UserMapper implements IMapper<UserDto, User> {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User map(UserDto userDto) {
        User userEntity = modelMapper.map(userDto, User.class);
        return userEntity;
    }

}
