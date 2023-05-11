package com.augusto.soloveganbusiness.services;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.augusto.soloveganbusiness.dto.UserDto;
import com.augusto.soloveganbusiness.exceptions.EntityAlreadyExistsException;
import com.augusto.soloveganbusiness.mappers.UserMapper;
import com.augusto.soloveganbusiness.models.User;
import com.augusto.soloveganbusiness.repositories.UserRepository;

@Service
public class UserService extends BaseService<UserDto, User> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDto registerUser(UserDto userDto) {
        // Validar si el usuario ya existe en la base de datos
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException("El email ya existe");
        }
        // Hashear la contraseña y guardar el usuario en la base de datos
        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        userDto.setPassword(hashedPassword);
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
