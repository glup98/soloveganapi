package com.augusto.soloveganbusiness.services;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.UserDto;
import com.augusto.soloveganbusiness.mappers.UserMapper;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.repositories.UserRepository;

@Service
public class UserService extends BaseService<User> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // Registrar el User y hacer Hash a su password
    public User registerUser(UserDto userDto) {
        User user = userMapper.map(userDto);
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
}
