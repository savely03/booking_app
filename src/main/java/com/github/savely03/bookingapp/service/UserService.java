package com.github.savely03.bookingapp.service;

import com.github.savely03.bookingapp.dto.UserDto;
import com.github.savely03.bookingapp.entity.Users;
import com.github.savely03.bookingapp.exception.UserNotFoundException;
import com.github.savely03.bookingapp.mapper.mapstruct.UserMapper;
import com.github.savely03.bookingapp.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@PreAuthorize("hasAuthority('MANAGER')")
public class UserService extends CrudService<Users, UserDto, Long> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserNotFoundException exception,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        super(userRepository, exception, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserDto create(UserDto dto) {
        Users user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    @CachePut(value = "users", key = "#id")
    @Override
    public UserDto update(Long id, UserDto dto) {
        return super.update(id, dto);
    }

    @Cacheable(value = "users")
    @Override
    public UserDto findById(Long id) {
        return super.findById(id);
    }

    @Transactional
    @CacheEvict(value = "users")
    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    public List<UserDto> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toDto)
                .toList();
    }

    public boolean existsByUsernameOrEmail(String username, String email, Long id) {
        if (id == null) {
            return userRepository.existsByUsernameOrEmail(username, email);
        }
        return userRepository.existsByUsernameOrEmail(username, email, id);
    }
}
