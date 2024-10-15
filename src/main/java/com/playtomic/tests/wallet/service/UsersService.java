package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.model.User;
import com.playtomic.tests.wallet.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
