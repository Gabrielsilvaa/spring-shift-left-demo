package com.demo.spring_shift_left_demo.service;

import com.demo.spring_shift_left_demo.model.User;
import com.demo.spring_shift_left_demo.repository.UserRepository;
import com.demo.spring_shift_left_demo.security.CryptoUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        // Hasheando a senha de forma vulnerável (MD5)
        String hashedPassword = CryptoUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
